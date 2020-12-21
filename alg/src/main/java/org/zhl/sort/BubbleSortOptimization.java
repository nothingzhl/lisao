package org.zhl.sort;

public class BubbleSortOptimization<T extends Comparable> implements Sort<T>{


    @Override
    public void sort(T[] t) {
        for (int out = 0; out < t.length; out++) {
            boolean flag =false;
            for (int in = 0; in < t.length-out-1; in++) {
                if (t[in].compareTo(t[in + 1]) > 0) {
                    swap(t,in,in+1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
