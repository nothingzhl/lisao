package org.zhl.search;

/**
 * 二分查找法
 *
 * @param <T>
 */
public class BSearch<T extends Comparable> implements Search<T> {
    @Override
    public int search(T[] t, T targetValue) {

        int low = 0;
        int high = t.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (t[mid].equals(targetValue)) {
                return mid;
            } else if (t[mid].compareTo(targetValue) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        return -1;
    }
}
