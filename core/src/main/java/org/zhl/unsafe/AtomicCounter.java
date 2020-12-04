package org.zhl.unsafe;

import sun.misc.Unsafe;

/**
 * 线程安全的计数器
 */
public class AtomicCounter {

    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public AtomicCounter(Unsafe unsafe) throws NoSuchFieldException {
        this.unsafe = unsafe;
        offset = unsafe.objectFieldOffset(AtomicCounter.class.getDeclaredField("counter"));
    }

    public void increment() {
        long expect = counter;
        while (!unsafe.compareAndSwapLong(this, offset, expect, expect + 1)) {
            expect = counter;
        }
    }

    public long getCounter() {
        return counter;
    }
}
