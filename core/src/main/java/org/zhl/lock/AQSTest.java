package org.zhl.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhanghanlin
 * @date 2023/5/19
 **/
public class AQSTest {

    public static int count = 0;

    static Lock lock = new ReentrantLock();

    public static void inc(){
        lock.lock();
        try {
            Thread.sleep(1);
            count ++;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> AQSTest.inc()).start();
        }
        System.out.println(AQSTest.count);
    }


}
