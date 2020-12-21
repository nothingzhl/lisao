package org.zhl.sort;

import java.lang.reflect.Array;

/**
 * 递归的公式:<br/>
 * merge_sort(p,r) = merge(merge_sort(p,q),merge_sort(q+1,r))
 * 终止条件:<br/>
 * p >=r
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable> implements Sort<T> {
    @Override
    public void sort(T[] t) {
        divideAndMerge(t, 0, t.length - 1);
    }

    /**
     * 分冶和merge
     *
     * @param t     数组
     * @param begin 开始
     * @param end   结束
     */
    private void divideAndMerge(T[] t, int begin, int end) {

        if (begin >= end) {
            return;
        }

        int q = (begin + end) / 2;

        divideAndMerge(t, begin, q);
        divideAndMerge(t, q + 1, end);

        merge(t, begin, q, end);
//        mergeBySentry(t,begin,q,end);
    }

    private void merge(T[] t, int p, int q, int r) {

        // 第一个子数组的第一个元素
        int i = p;
        // 第二个子数组的第一个元素
        int j = q + 1;
        // tmp的index
        int k = 0;

        // 临时数组
        T[] tmp = (T[]) Array.newInstance(t.getClass().getComponentType(), r-p+1);

        // 合并数组
        while (i <= q && j <= r) {
            if (t[i].compareTo(t[j]) <= 0) {
                tmp[k++] = t[i++];
            } else {
                tmp[k++] = t[j++];
            }
        }

        // 判断那个子数组中有剩余的数据
        int start = i;
        int end = q;

        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据copy到临时数组中
        while (start <= end) {
            tmp[k++] = t[start++];
        }

        // 将tmp中的数据copy回原数组
        for ( i = 0; i <= r-p; i++) {
            t[p + i] = tmp[i];
        }

    }

    /**
     * 利用哨兵去合并
     * @param t
     * @param p
     * @param q
     * @param r
     */
    private void mergeBySentry(T[] t, int p, int q, int r){

        T[] left = (T[])Array.newInstance(t.getClass().getComponentType(),q-p+2);
        T[] right  = (T[])Array.newInstance(t.getClass().getComponentType(),r-q+2);

        for (int i = 0; i < q - p; i++) {
            left[i] =t[p+i];
        }

        // 添加哨兵，null 不是哨兵
        left[q-p+1] = null;

        for (int i = 0; i < r - q; i++) {
            right[i] = t[q+1+1+i];
        }
        // 添加哨兵，null 不是哨兵
        right[r-1] = null;

        int i =0;
        int j =0;
        int k = p;

        while (k<=r){
            //当左边的数组到达哨兵时，i不再增加,直到右边数组读取完剩余值，同理右边也一样
            // 数组的最后一个为哨兵，哨兵会使该等式永远为一个分支
            if (left[i].compareTo(right[i])<=0)  {
                t[k++] =left[i++];
            }else {
                t[k++] = right[j++];
            }
        }


    }

}
