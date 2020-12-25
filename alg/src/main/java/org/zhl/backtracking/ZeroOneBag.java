package org.zhl.backtracking;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 0/1背包问题
 */
public class ZeroOneBag {

    // 存储背包中物品总重量的最大值
    public int maxW = Integer.MAX_VALUE;

    /**
     * @param i     考察到哪个物品
     * @param cw    当前已经装进去的物品重量和
     * @param items 每个物品重量
     * @param n     物品个数
     * @param w     背包重量
     */
    public void fill(int i, int cw, int[] items, int n, int w) {
        // 背包装满或者物品考察完
        if (cw == w || i == n) {
            // i ==n 时判断
            if (cw > maxW) {
                maxW = cw;
            }
            System.out.println(maxW);
            return;
        }

        // 当前物品不装进bag
        fill(i + 1, cw, items, n, w);

        // 已经超过可以背包承受的重量的时候，就不在装了（剪枝）
        if (cw + items[i] <= w) {
            // 当前物品装进bag
            fill(i + 1, cw + items[i], items, n, w);
        }

    }

    public static void main(String[] args) {
        int[] a = new int[10];
        IntStream.range(0, 10).forEach(i -> {
            a[i] = new Random().nextInt(30) + 20;
        });
        new ZeroOneBag().fill(0, 0, a, 10, 100);
    }

}
