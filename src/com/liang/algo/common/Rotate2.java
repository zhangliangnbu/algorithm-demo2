package com.liang.algo.common;

/**
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class Rotate2 {

    public static void main(String[] args) {
        Rotate2 obj = new Rotate2();
        int[] nums = {-1,-100,3,99};
        int k = 2;
        obj.rotate3(nums, k);
    }

    public void rotate(int[] nums, int k) {
        // 一步一步移动 O(m*k)
        if (nums == null || nums.length == 0) {
            return;
        }
        if (k >= nums.length) {
            k = k % nums.length;
        }
        if (k == 0) {
            return;
        }
        for (int i = k; i > 0; i --) {
            int temp = nums[nums.length - 1];
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            nums[0] = temp;
        }
    }

    public void rotate2(int[] nums, int k) {
        // 一步一步移动 O(m*k) 递归
        if (nums == null || nums.length == 0) {
            return;
        }
        if (k >= nums.length) {
            k = k % nums.length;
        }
        if (k == 0) {
            return;
        }
        int temp = nums[nums.length - 1];
        System.arraycopy(nums, 0, nums, 1, nums.length - 1);
        nums[0] = temp;
        k --;
        rotate2(nums, k);
    }

    public void rotate3(int[] nums, int k) {
        // 环状、跳跃移动直接到位 O(n)
        if (nums == null || nums.length == 0) {
            return;
        }
        if (k >= nums.length) {
            k = k % nums.length;
        }
        if (k == 0) {
            return;
        }
        // 因为每个元素移动到之后的第k的位置，直接到位，因此只要每个元素移动一次就可以了
        int n = nums.length;
        int count = 0;
        int pre = nums[0];
        int currentIndex = 0;
        for (int i = 0; count < n; i ++) {
            pre = nums[i];
            currentIndex = i;
            do {
                int temp = pre;
                currentIndex = (currentIndex + k) % n;
                pre = nums[currentIndex];
                nums[currentIndex] = temp;
                count ++;
            } while (currentIndex != i);
        }
    }

    public void rotate4(int[] nums, int k) {
        // 反转 O(n)
        if (nums == null || nums.length == 0) {
            return;
        }
        if (k >= nums.length) {
            k = k % nums.length;
        }
        if (k == 0) {
            return;
        }
        int n = nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end --;
        }
    }
}
