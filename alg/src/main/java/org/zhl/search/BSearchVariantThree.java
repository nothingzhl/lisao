package org.zhl.search;

/**
 * 二分查找变种:<br/>
 * 查找第一个大于等于给定值的元素
 *
 * @param <T>
 */
public class BSearchVariantThree<T extends Comparable> implements Search<T> {
    @Override
    public int search(T[] t, T targetValue) {

        int low = 0;
        int high = t.length - 1;

        while (low <= high) {

            int mid = low + ((high - low) >> 1);

            if (t[mid].compareTo(targetValue) > 0) {
                if (mid == 0 || t[mid - 1].compareTo(targetValue) < 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }

        }

        return 0;
    }
}
