package org.zhl.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.stream.IntStream;

/**
 * @author zhanghanlin
 * @date 2022/9/7
 **/
public class MaxSubArray {

    @Test
    void test_6() {
        Assertions.assertEquals(6, maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void test_1() {
        Assertions.assertEquals(1, maxSubArray(new int[] {1}));
    }

    @Test
    void test_23() {
        Assertions.assertEquals(23, maxSubArray(new int[] {5, 4, -1, 7, 8}));
        Assertions.assertEquals(23, maxSubArrayV2(new int[] {5, 4, -1, 7, 8}));
    }

    public int maxSubArray(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= nums.length; i++) {
            final int tempMax = theMaxOfWindows(nums, i);
            if (max < tempMax) {
                max = tempMax;
            }
        }
        return max;
    }

    private int theMaxOfWindows(int[] nums, int winLent) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if ((i + winLent) > nums.length) {
                break;
            }
            final int tempMax = sum(nums, i, winLent);
            if (max < tempMax) {
                max = tempMax;
            }
        }
        return max;
    }

    private int sum(int[] nums, int leftPoint, int winLen) {
        if (winLen == 1) {
            return nums[leftPoint];
        } else {
            int temp[] = new int[winLen];
            System.arraycopy(nums, leftPoint, temp, 0, winLen);
            return IntStream.of(temp).sum();
        }
    }

    public int maxSubArrayV2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
