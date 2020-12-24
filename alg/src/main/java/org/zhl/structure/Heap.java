package org.zhl.structure;

/**
 * 堆:<br/>
 * 利用数组来保存堆，堆是完全二叉树<br/>
 * 数组中下标为i的节点的左子节点，就是下标为i*2的节点<br/>
 * 右节点为i*2+1<br/>
 * 父节点为i/2<br/>
 */
public class Heap {

    /**
     * 数组，从下标1开始存储数据
     */
    private int[] a;

    /**
     * 堆可以存储的最大数据个数
     */
    private int n;

    /**
     * 堆中已经存储的数据个数
     */
    private int count;

    /**
     * @param n
     */
    public Heap(int n) {
        a = new int[n];
        this.n = n;
        count = 0;
    }

    /**
     * 插入堆
     *
     * @param data
     */
    public void insert(int data) {
        // 堆满了
        if (count > n) {
            return;
        }

        count++;

        a[count] = data;

        int i = count;

        // 自下往上堆化
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }

    }

    public void removeMax() {
        // 堆中没有数据
        if (count == 0) {
            return;
        }

        // 将最后的赋值到对顶
        a[1] = a[count];
        count--;
        heapify(a, count, 1);
    }

    /**
     * 自上往下堆化
     *
     * @param a
     * @param count
     * @param i
     */
    private void heapify(int[] a, int count, int i) {
        while (true) {
            int maxPos = i;
            // 小于左子节点
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            // 小于右子节点
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 已经最大了
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            // 重置节点
            i = maxPos;
        }
    }

    /**
     * 交换
     *
     * @param a
     * @param i
     * @param j
     */
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int getMaxValue() {
        return a[1];
    }

}
