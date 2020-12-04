package org.zhl.aqs;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * 探究aqs的测试类
 */
class ReentrantLockTest {

    @Test
    void name() {
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = ()->{
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        };

        IntStream.range(0,10)
                .mapToObj(item->new Thread(runnable))
                .forEach(Thread::start);

        System.out.println("1");
    }
}
