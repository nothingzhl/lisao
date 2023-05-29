package org.zhl.array;

/**
 * @author zhanghanlin
 * @date 2023/5/24
 **/
public class SplitArray {

    public static int split(int[] nums){

        int n = nums.length;
        int leftMax = nums[0], leftPos = 0, curMax = nums[0];

        for (int i = 1; i < n - 1; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        return leftPos + 1;

    }

    public static void main(String[] args) {
        int[] arr = {5,0,3,8,6};
        final int split = SplitArray.split(arr);
        System.out.println(split);
    }
}
