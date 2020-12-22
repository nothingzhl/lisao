package org.zhl.search;

/**
 * 二分查找变种一:<br/>
 * 查找第一个值等于给定值的元素
 */
public class BSearchVariantOne<T extends Comparable> implements Search<T> {

    @Override
    public int search(T[] t, T targetValue) {

        return bSearchInternally(t, 0, t.length - 1, targetValue);
    }

    /**
     * 利用递归来查找
     *
     * @param t
     * @param low
     * @param high
     * @param targetValue
     *
     * @return
     */
    private int bSearchInternally(T[] t, int low, int high, T targetValue) {

        if (low > high) {
            return -1;
        }

        int mid = low + (high-low >>1);

        if (t[mid].compareTo(targetValue) <0) {
            return bSearchInternally(t,mid+1,high,targetValue);
        }else if (t[mid].compareTo(targetValue)>0){
            return bSearchInternally(t,low,mid-1,targetValue);
        }else {
            // 数组第一个数据是mid，或者mid前一个不是target
            if (mid ==0 || !t[mid-1].equals(targetValue)) {
                return mid;
            }else {
                return bSearchInternally(t,low,mid-1,targetValue);
            }
        }

    }

}
