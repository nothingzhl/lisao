package org.zhl.array;

import java.util.Arrays;

/**
 * 买卖股票最好的时机
 */
public class GoodTime {

    public static void main(String[] args) {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        int i = maxProfit(prices);
        System.out.println(i);

        int dynamic = dynamic(prices);
        System.out.println(dynamic);
    }

    public static int maxProfit(int[] prices) {

        int money = 0;

        for (int i = 1; i < prices.length; i++) {
            money += Math.max(0, prices[i] - prices[i - 1]);
        }

        return money;
    }

    public static int dynamic(int[] prices) {

        // 0表示没有股票，1表示持有股票
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 设置初始状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];

    }

    /**
     * 回溯
     *
     * @param prices
     *
     * @return
     */
    public static int maxProfitDfs(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        return 0;
    }

    /**
     * @param prices 股价数组
     * @param index  第几天
     * @param status 0/1 表示是否持有
     * @param res    当前收益
     */
    public void des(int[] prices, int index, int status, int res) {

        // 递归结束条件
        if (index == prices.length) {

            return;
        }

        des(prices, index + 1, status, res);

        if (status == 0) {
            des(prices, index + 1, 1, res);

        } else {
            des(prices, index + 1, 0, res);

        }

    }

}
