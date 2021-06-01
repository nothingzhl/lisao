package org.zhl.cf;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-05-31 16:46
 **/
public class CompletableFutureTest {

    private static final ScheduledExecutorService scheduler = Executors
        .newScheduledThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).setNameFormat("failAfter-%d").build());
    private final ScheduledExecutorService delayer = Executors.newSingleThreadScheduledExecutor();

    public static <T> CompletableFuture<T> failAfter(Duration duration) {
        final CompletableFuture<T> promise = new CompletableFuture<>();
        scheduler.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return promise.completeExceptionally(ex);
        }, duration.toMillis(), TimeUnit.MILLISECONDS);
        return promise;
    }

    public static <T> CompletableFuture<T> within(CompletableFuture<T> future, Duration duration) {
        final CompletableFuture<T> timeout = failAfter(duration);
        return future.applyToEither(timeout, Function.identity());
    }

    @Test
    void testComplete() {
        final CompletableFuture<String> helloCF = CompletableFuture.completedFuture("hello world");
        assertNotNull(helloCF);
        assertEquals("hello world", helloCF.getNow(null));
    }

    @Test
    void testCompleteRun() {
        CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
    }

    private void randomSleep() {
        try {
            Thread.sleep(RandomUtils.nextInt(1, 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCompleteThenApply() {
        // 同步
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
        // 异步
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        assertNull(cf2.getNow(null));
        assertEquals("MESSAGE", cf2.join());
    }

    @Test
    void testCompleteThenAccept() {
        StringBuilder result = new StringBuilder();
        // 同步
        CompletableFuture.completedFuture("thenAccept message").thenAccept(s -> result.append(s));
        assertTrue(result.length() > 0, "Result was empty");
        // 异步
        CompletableFuture<Void> cf =
            CompletableFuture.completedFuture("thenAcceptAsync message").thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue(result.length() > 0, "Result was empty");
    }

    @Test
    void testEither() {
        String original = "Message";
        CompletableFuture<String> cf1 =
            CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s));
        CompletableFuture<String> cf2 =
            cf1.applyToEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                s -> s + " from applyToEither");
        assertTrue(cf2.join().endsWith(" from applyToEither"));
    }

    @Test
    void testEitherAny() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf =
            CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s))
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                    s -> result.append(s).append("acceptEither"));
        cf.join();
        assertTrue(result.toString().endsWith("acceptEither"), "Result was empty");
    }

    @Test
    void testBoth() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
            .runAfterBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));

        assertTrue(result.length() > 0, "Result was empty");
    }

    private String delayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
    }

    private String delayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    @Test
    void testCompleteFutureBiComsumer() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
            .thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }

    @Test
    void testCompleteFutureBiFunction() {
        String original = "Message";
        // 同步
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
            .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
                (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf.getNow(null));
        // 异步
        CompletableFuture<String> cf1 =
            CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                    (s1, s2) -> s1 + s2);
        assertEquals("MESSAGEmessage", cf1.join());
    }

    @Test
    void testCompose() {
        String original = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
            .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    @Test
    void testException() {
        CompletableFuture<Integer> x = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Oops, something went wrong");
            }
            return 42;
        });
        // Note that tryX and x are of same type.
        CompletableFuture<Integer> tryX = x.exceptionally(ex -> -1);
        assertEquals(-1, tryX.getNow(0));
    }

    @Test
    @RepeatedTest(10)
    void testHandle() {
        CompletableFuture<Integer> x = CompletableFuture.supplyAsync(() -> {
            if (RandomUtils.nextInt(0, 1) > 0) {
                throw new RuntimeException("Oops, something went wrong");
            }
            return 42;
        });

        CompletableFuture<Integer> tryX = x.handle((value, ex) -> { // Note that tryX and x are of different type.
            if (value != null) {
                return value;
            } else {
                return -1;
            }
        });

        tryX.whenComplete((v, e) -> assertTrue(Arrays.asList(-1, 42).contains(v)));

        tryX.join();
    }

    @Test
    void testTransmit() {
        CompletableFuture<Integer> x = CompletableFuture.supplyAsync(() -> {
            if (RandomUtils.nextInt(0, 1) > 0) {
                throw new RuntimeException("Oops, something went wrong");
            }
            return 42;
        });
        // Note that tryX and x are of same type. This CompletableFuture acts as an invisible "decorator".
        CompletableFuture<Integer> tryX = x.whenComplete((value, ex) -> {
            if (value != null) {
                System.out.println("Result: " + value);
            } else {
                System.out.println("Error code: -1. Root cause: " + ex.getMessage());
            }
        });
    }

    @Test
    void testTimeoutAbort() {
        assertThrows(Exception.class, () -> {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).acceptEither(timeoutAfter(1, TimeUnit.SECONDS), result -> System.out.println("The result is: " + result))
                .join();
        });

    }

    public <T> CompletableFuture<T> timeoutAfter(long timeout, TimeUnit unit) {
        CompletableFuture<T> promise = new CompletableFuture<T>();
        promise.thenRun(() -> System.out.println("hello world"));
        delayer.schedule(() -> promise.completeExceptionally(new TimeoutException()), timeout, unit);
        return promise;
    }

    @Test
    void testExceptionWithin() {
        within(CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), Duration.ofSeconds(1)).thenRun(() -> System.out.println("hello world")).exceptionally(throwable -> {
            System.out.println("error");
            return null;
        }).join();
    }
}
