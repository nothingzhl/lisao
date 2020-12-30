package org.zhl.array;

/**
 * 旋转数组
 */
public class SpinArray {

    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    public void rotateWithTemp(int[] nums, int k) {
        int[] temp = new int[k];

        for (int j = 0, i = nums.length - k; i < nums.length; i++, j++) {
            temp[j] = nums[i];
        }

        System.arraycopy(temp, 0,  nums, 0, k);

    }
}
