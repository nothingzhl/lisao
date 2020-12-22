package org.zhl.sort;

/**
 * 计数排序
 */
public class CountingSort {

    public void sort(Integer[] t) {

        if (t.length <= 1) {
            return;
        }

        // 查找数组中的数据的范围
        int max = t[0];
        for (int i = 0; i < t.length; i++) {
            if (max < t[i]) {
                max = t[i];
            }
        }

        // 申请一个计数数组c,下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < t.length; i++) {
            c[t[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r,存储排序之后的结果
        int[] r = new int[t.length];

        // 计算排序的关键步骤
        for (int i = t.length - 1; i >= 0; i--) {
            int index = c[t[i]] - 1;
            r[index] = t[i];
            c[t[i]]--;
        }

        // 将结果copy给原数组
        for (int i = 0; i < t.length; i++) {
            t[i] = r[i];
        }

    }

}
