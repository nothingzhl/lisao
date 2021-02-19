package org.zhl.array;

/**
 * 动态数组和
 * leetcode 1480
 */
public class DynamicArraySum {

    public int[] runningSum(final int[] nums) {

        int currentMax = nums[0];

        final int[] target = new int[nums.length];

        target[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax += nums[i];
            target[i] = currentMax;
        }

        return target;

    }

    public int[] runningSumWithOpt(final int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        return nums;

    }

}
