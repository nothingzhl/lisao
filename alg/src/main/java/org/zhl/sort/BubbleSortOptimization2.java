package org.zhl.sort;

/**
 * 鸡尾酒排序
 * @author zhanghanlin
 * @date 2022/4/20
 **/
public class BubbleSortOptimization2<T extends Comparable> implements Sort<T>{

    @Override
    public void sort(T[] t) {
        for (int out = 0; out < (t.length-1)/2; out++) {

            boolean isSort = true;

            // 第一轮排序
            for (int in = 0; in < t.length - 1 - out; in++) {
                if (t[in].compareTo(t[in+1]) > 0){
                    swap(t,in,in+1);
                    isSort =false;
                }
            }

            if (isSort){
                break;
            }

            isSort = true;

            //  第二轮排序
            for (int in=t.length-2-out;in >0;in--){
                if (t[in].compareTo(t[in-1])<0){
                    swap(t,in,in-1);
                    isSort =false;
                }
            }

            if (isSort){
                break;
            }

        }
    }
}
