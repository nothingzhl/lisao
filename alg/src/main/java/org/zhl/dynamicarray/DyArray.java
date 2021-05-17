package org.zhl.dynamicarray;

/**
 * @program: lisao
 * @description: 动态数组
 * @author: zhanghanlin
 * @create: 2021-05-17 20:39
 **/
public class DyArray<T> {

    private final static int DEFAULT_CAP = 16;

    /**
     * 底层数组
     */
    private T[] origin;

    /**
     * 使用中的数组索引
     */
    private int index;

    public DyArray() {
        origin = (T[])new Object[DEFAULT_CAP];
        index = 0;
    }

    /**
     * 插入新值
     *
     * @param value
     */
    public void pushBack(T value) {
        resize();
        origin[index] = value;
        index++;
    }

    /**
     * 弹出新值
     *
     * @return
     */
    public T pop_back() {
        if (index < 0) {
            return null;
        }
        return origin[index--];
    }

    public int size() {
        return index;
    }

    public T index(int index) {
        if (index < 0 && index >= size()) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        return origin[index];
    }

    /**
     * 数组的1/2 <index 做扩容
     */
    private void resize() {
        if ((origin.length >> 2) < index) {
            T[] temp = (T[])new Object[origin.length * 2];
            System.arraycopy(origin, 0, temp, 0, origin.length);
            origin = temp;
        }
    }

}
