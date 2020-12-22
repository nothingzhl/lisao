package org.zhl.search;

/**
 * 二分查找变种一:<br/>
 * 查找最后一个值等于给定值的元素
 */
public class BSearchVariantTwo<T extends Comparable> implements Search<T> {
    @Override
    public int search(T[] t, T targetValue) {

        int low = 0;
        int high = t.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);

            if (t[mid].compareTo(targetValue) < 0) {
                low = mid + 1;
            } else if (t[mid].compareTo(targetValue) > 0) {
                high = mid - 1;
            } else {
                if (mid == t.length - 1 || !t[mid + 1].equals(targetValue)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }
}
