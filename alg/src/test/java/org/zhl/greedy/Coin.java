package org.zhl.greedy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghanlin
 * @date 2022/10/17
 **/
public class Coin {

    @Test
    void coinCount() {
        int[] values = {5, 3};
        int k = 11;
        final int theBestSolve = getTheBestSolve(values, k);
        System.out.println(theBestSolve);
    }

    private int getTheBestSolve(int[] values, int k) {
        List<Integer> temp = new ArrayList<>();
        int result = k;
        int countResult = 0;
        for (int value : values) {
            int count = result / value;
            result -= value * count;
            for (int i = 0; i < count; i++) {
                temp.add(value);
            }
            countResult += count;
            if (result == 0) {
                System.out.println(temp);
                return countResult;
            }
        }
        System.out.println(temp);
        return -1;
    }

    @Test
    void test_2() {
        int[] values = {1,2,5};
        int k = 11;
        final int theBestSolve = getTheBestSolve2(values, k);
        System.out.println(theBestSolve);
    }



    private int getTheBestSolve2(int[] coins, int amount) {


        for (int i = coins.length-1; i > 0; i--) {
            for (int j = coins.length-1; j > 0; j--) {
                if (coins[j] > coins[j-1]){
                    int t = coins[j];
                    coins[j] = coins[j-1];
                    coins[j-1] = t;
                }
            }
        }

        int result = amount;
        int countResult = 0;

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            int value = coins[i];
            int count = result / value;
            result -= value * count;

            countResult += count;

            for (int j = 0; j < count ; j++) {
                temp.add(value);
            }

            if (result == 0) {
                System.out.println(temp);
                return countResult;
            }

            // 回溯
            if (i < coins.length - 1 && result < coins[i + 1] && count != 0) {
                result += value;
                countResult--;
                if (temp.size() != 0){
                    temp.remove(temp.size()-1);
                }
            }
        }

        return -1;
    }

    int getMinCoinCountOfValue(int total, int[] values, int valueIndex) {
        int valueCount = values.length;
        if (valueIndex == valueCount) {
            return Integer.MAX_VALUE;
        }

        int minResult = Integer.MAX_VALUE;
        int currentValue = values[valueIndex];
        int maxCount = total / currentValue;

        for (int count = maxCount; count >= 0; count--) {
            int rest = total - count * currentValue;

            // 如果rest为0，表示余额已除尽，组合完成
            if (rest == 0) {
                minResult = Math.min(minResult, count);
                break;
            }

            // 否则尝试用剩余面值求当前余额的硬币总数
            int restCount = getMinCoinCountOfValue(rest, values, valueIndex + 1);

            // 如果后续没有可用组合
            if (restCount == Integer.MAX_VALUE) {
                // 如果当前面值已经为0，返回-1表示尝试失败
                if (count == 0) {
                    break;
                }
                // 否则尝试把当前面值-1
                continue;
            }

            minResult = Math.min(minResult, count + restCount);
        }

        return minResult;
    }

    int getMinCoinCountLoop(int total, int[] values, int k) {
        int minCount = Integer.MAX_VALUE;
        int valueCount = values.length;

        if (k == valueCount) {
            return Math.min(minCount, getMinCoinCountOfValue(total, values, 0));
        }

        for (int i = k; i <= valueCount - 1; i++) {
            // k位置已经排列好
            int t = values[k];
            values[k] = values[i];
            values[i] = t;
            minCount = Math.min(minCount, getMinCoinCountLoop(total, values, k + 1)); // 考虑后一位

            // 回溯
            t = values[k];
            values[k] = values[i];
            values[i] = t;
        }

        return minCount;
    }

    @Test
    void getMinCoinCountOfValue() {
        int[] values = {5, 3}; // 硬币面值
        int total = 11; // 总价
        int minCoin = getMinCoinCountLoop(total, values, 0);

        int a = (minCoin == Integer.MAX_VALUE) ? -1 : minCoin;  // 输出答案
    }

    public int min (int a ,int b){
        return a > b ? b :a;
    }


}
