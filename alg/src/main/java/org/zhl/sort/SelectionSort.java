package org.zhl.sort;

public class SelectionSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] t) {

        for (int i = 0; i < t.length; i++) {
            int minIndex = findMin(t, i);
            swap(t, i, minIndex);
        }

    }

    /**
     * min 的边界值
     *
     * @param t
     * @param index
     *
     * @return
     */
    public int findMin(T[] t, int index) {

        int minIndex = index;

        for (int i = index; i < t.length; i++) {
            if (t[i].compareTo(t[minIndex]) < 0) {
                minIndex = i;
            }
        }

        return minIndex;
    }
}
