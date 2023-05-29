import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.junit.jupiter.api.*;
import org.zhl.Product;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghanlin
 * @date 2022/12/22
 **/
public class RedisTest {
    private static RedisClient redisClient;

    private static StatefulRedisConnection<String, String> connection;

    private static RedisAsyncCommands<String, String> asyncCommands;

    @BeforeEach
    public void before() {
        redisClient = RedisClient.create("redis://15614154710@tc.zhanghanlin.club:6380/0");
        connection = redisClient.connect();
        asyncCommands = connection.async();
    }

    @AfterEach
    public void after() {
        connection.close();
        redisClient.shutdown();
    }

    @Test
    public void testCacheProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Product product = new Product();
        product.setName("杯子");
        product.setPrice(100d);
        product.setDesc("这是一个杯子");
        String json = objectMapper.writeValueAsString(product);

        asyncCommands.set("product", json).get(1, TimeUnit.SECONDS);
    }


    @Test
    public void testGetProduct() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = asyncCommands.get("product").get(1, TimeUnit.SECONDS);
        Product product = objectMapper.readValue(json, new TypeReference<Product>() {
        });
        System.out.println(product);
    }

    @Test
    public void testLock() throws Exception {
        int threadNum = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Runnable runnable = () -> {
            try {
                countDownLatch.await();
                while (true) {
                    // 获取锁
                    SetArgs setArgs = SetArgs.Builder.ex(5).nx();
                    String succ = asyncCommands.set("update-product",
                        Thread.currentThread().getName(), setArgs).get(1, TimeUnit.SECONDS);
                    System.out.println(succ);
                    // 加锁失败
                    if (!"OK".equals(succ)) {
                        System.out.println(Thread.currentThread().getName() + "加锁失败，自选等待锁");
                        Thread.sleep(100);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "加锁成功");
                        break;
                    }
                }
                // 加锁成功
                System.out.println(Thread.currentThread().getName() + "开始执行业务逻辑");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "完成业务逻辑");
                // 释放锁
                asyncCommands.del("update-product").get(1, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
        thread2.start();
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
        thread3.start();
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());

        Thread.sleep(TimeUnit.DAYS.toMillis(1));
    }

    @Test
    public void testLimit() throws Exception {
        String prefix = "order-service";
        long maxQps = 10;
        long nowSeconds = System.currentTimeMillis() / 1000;
        for (int i = 0; i < 15; i++) {
            Long result = asyncCommands.incr(prefix + nowSeconds).get(1, TimeUnit.SECONDS);
            if (result > maxQps) {
                System.out.println("请求被限流");
            }else{
                System.out.println("请求正常被处理");
            }
        }
    }


}
