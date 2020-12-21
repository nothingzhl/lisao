package org.zhl.sort;

/**
 * 排序接口
 */
public interface Sort<T extends Comparable> {

    void sort(T[] t);

    /**
     * 交换
     *
     * @param t
     * @param originIndex
     * @param targetIndex
     */
    default void swap(T[] t, int originIndex, int targetIndex) {
        T temp = t[originIndex];
        t[originIndex] = t[targetIndex];
        t[targetIndex] = temp;
    }

    /**
     * 是否有序
     *
     * @param t
     *
     * @return
     */
    default boolean isSort(T[] t) {

        boolean flag1 = false;
        boolean flag2 = false;

        for (int i = 0; i < t.length - 1; i++) {
            if (t[i].equals(min(t[i],t[i+1]))) {
                flag1 = true;
            }else {
                flag1 =false;
                break;
            }
        }

        for (int i = 0; i < t.length - 1; i++) {
            if (t[i].equals(max(t[i],t[i+1]))) {
                flag2 = true;
            }else {
                flag2 =false;
                break;
            }
        }

        return flag1 || flag2;
    }

    default T min(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    default T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

}
