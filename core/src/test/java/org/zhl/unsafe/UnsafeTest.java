package org.zhl.unsafe;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

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
    void testToAddress() {
        Object[] objects = new Object[2];
        int i = unsafe.arrayBaseOffset(objects.getClass());
        int anInt = unsafe.getInt(objects, i);

    }
}
