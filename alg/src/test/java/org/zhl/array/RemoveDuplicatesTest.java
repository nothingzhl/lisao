package org.zhl.array;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * <p>leetCode 26</p>
 *
 * @author zhanghanlin
 * @date 2022/4/29
 **/
public class RemoveDuplicatesTest {

    @Test
    void testRemoveDuplicatestTest() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}; // 输入有序的数组
        int[] expectedNums = {0, 1, 2, 3, 4}; // 长度正确的期望答案


        int k = removeDuplicate(nums); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    private int removeDuplicate(int[] nums) {

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }

        return index + 1;
    }

}
