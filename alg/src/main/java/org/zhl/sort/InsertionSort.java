package org.zhl.sort;

public class InsertionSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] t) {

        if (t.length < 1) {
            return;
        }

        for (int i = 1; i < t.length; i++) {
            T currentValue = t[i];
            int preIndex = i - 1;

            while (preIndex >= 0){
                if (t[preIndex].compareTo(currentValue) > 0) {
                    t[preIndex +1] =t[preIndex];
                }else {
                    break;
                }
                preIndex--;
            }

            t[preIndex+1] = currentValue;
            
        }

    }
}
