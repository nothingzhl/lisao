package org.zhl.array;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * 删除数组中的重复选项
 */
public class RepeatArrayRemove {

    public static void main(String[] args) {

        int[] nums = new int[] {
                1, 1, 2, 2, 3
        };

        int i = removeDuplicatesTwo(nums);
        System.out.println(Arrays.toString(nums));

    }

    public static int removeDuplicates(int[] nums) {

        Set<Integer> temp = new TreeSet<>();

        for (int num : nums) {
            temp.add(num);
        }

        Integer[] integers = temp.toArray(new Integer[0]);

        for (int i = 0; i < integers.length; i++) {
            nums[i] = integers[i];
        }

        return temp.size();
    }

    public static int removeDuplicatesTwo(int[] nums) {

        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            // 重复的话，j 后移
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;

    }

}
