package org.zhl.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author zhanghanlin
 * @date 2022/9/7
 **/
public class ContainsDuplicate {

    @Test
    void testFalse() {
        Assertions.assertFalse(containsDuplicate(new int[] {1, 2, 3, 4}));
    }

    @Test
    void testTure() {
        Assertions.assertTrue(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

    private boolean containsDuplicate(int[] nums) {
        Set<Integer> sets = new HashSet<>(nums.length);
        for (int num : nums) {
            if (sets.contains(num)) {
                return true;
            }
            sets.add(num);
        }
        return false;
    }
}
