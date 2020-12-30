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

    /**
     * 动态规划版 背包问题
     *
     * @param weight 物品重量
     * @param n      物品个数
     * @param w      背包可承载重量
     *
     * @return
     */
    public int dynamic(int[] weight, int n, int w) {

        boolean[][] states = new boolean[n][w + 1];

        // 初始化数据
        states[0][0] = true;
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        //动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < w; j++) { // 不放第i个物品
                if (states[i - 1][j] = true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j < w - weight[i]; j++) { // 放第i个物品
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i] == true) {
                return i;
            }
        }

        return 0;
    }

}
