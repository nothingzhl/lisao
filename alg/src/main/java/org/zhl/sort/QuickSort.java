package org.zhl.sort;

/**
 * 利用归并排序的思想，不过分区不一样
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable> implements Sort<T> {

    @Override
    public void sort(T[] t) {

        quickSort(t, 0, t.length - 1);

    }

    private void quickSort(T[] t, int p, int r) {

        if (p >= r) {
            return;
        }

        int q = partition(t, p, r);
        quickSort(t, p, q - 1);
        quickSort(t, q + 1, r);
    }

    /**
     * 原地分区函数
     *
     * @param t
     * @param p left
     * @param r right
     *
     * @return
     */
    private int partition(T[] t, int p, int r) {

        // 中枢点
        T pivot = t[r];

        // left
        int i = p;

        // 类似选择排序的思想，利用快慢指针来进行分区
        for (int j = p; j < r; j++) {
            if (t[j].compareTo(pivot) < 0) {
                if (i != j) {
                    swap(t, i, j);
                }
                i++;
            }
        }

        swap(t, i, r);
        return i;
    }

}
