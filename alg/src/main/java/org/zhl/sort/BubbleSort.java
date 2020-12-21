package org.zhl.sort;

/**
 * 冒泡排序
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] t) {

        for (int out = 0; out < t.length; out++) {
            for (int in = 0; in < t.length-out-1; in++) {
                if (t[in].compareTo(t[in + 1]) > 0) {
                    swap(t,in,in+1);
                }
            }
        }

    }

}
