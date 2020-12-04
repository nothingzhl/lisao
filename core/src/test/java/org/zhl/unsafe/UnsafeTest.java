package org.zhl.unsafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import sun.misc.Cleaner;
import sun.misc.Unsafe;

/**
 * Unsafe
 * @author zhanghanlin
 */
public class UnsafeTest {

    private Unsafe unsafe = getUnsafe();

    /**
     * 通过反射避免Unsafe.getUnsafe的安全检测
     * unsafe的获取需要是引导类加载才合法
     * @return
     */
    private static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e){
            return null;
        }
    }

    @Test
    void testGetUnsafe() {
        assertNotNull(unsafe);
    }


    @Test
    void testMemoryMethodWithNumber() {
        // 分配内存, 相当于C++的malloc函数
        long address = unsafe.allocateMemory(4L);
        // 扩充内存
        long reAddress = unsafe.reallocateMemory(address, 4L);
        assertEquals(address,reAddress);
        // 释放内存
        unsafe.freeMemory(address);
    }

    /**
     * @see java.nio.DirectByteBuffer
     */
    @Test
    void testDirectMem() {

        long size = 1024L;

        // base address
        long base = unsafe.allocateMemory(size);
        unsafe.setMemory(base,size,(byte)0);

        Cleaner.create(this,()->{
            unsafe.freeMemory(base);
        });
    }

    @Test
    void testSystemMethod() {
        // 系统指针大学
        int systemPointSize = unsafe.addressSize();
        System.out.println(systemPointSize);
        // 内存也的大小
        int pageSize = unsafe.pageSize();
        System.out.println(pageSize);
    }

    @Test
    void testMemoryMethodWithClass() throws Exception {

        // 避开构造方法初始化对象，使用allocateInstance
        UnsafeClass uc =(UnsafeClass)unsafe.allocateInstance(UnsafeClass.class);
        assertNotNull(uc);
        uc.setData("hello world");

        // 修改对象成员值
        Field data = UnsafeClass.class.getDeclaredField("data");
        unsafe.putObject(uc,unsafe.objectFieldOffset(data),"good job");
        assertEquals(uc.getData(),"good job");
    }

    @Test
    void testAtomicCounter() throws Exception {
        AtomicCounter atomicCounter = new AtomicCounter(unsafe);
        List<CompletableFuture<Void>> result = IntStream.range(0, 1000)
                .mapToObj(it -> CompletableFuture.runAsync(() -> atomicCounter.increment(),
                        Executors.newFixedThreadPool(100)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(result.toArray(new CompletableFuture[0])).join();
        assertEquals(1000,atomicCounter.getCounter());
    }
}
