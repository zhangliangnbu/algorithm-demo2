package com.liang.algo.dynamic;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {

    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        boolean changed = false;
        for (int i = nums.length - 2; i >= 0; i --) {
            if (nums[i] < nums[i + 1]) {
                changed = true;
                // i+1 ~ nums.length-1递减
                Arrays.sort(nums, i+1, nums.length);
                // 交换刚刚比i+1值大的值
                int p = i + 1;
                while (p <= nums.length - 1) {
                    if (nums[p] > nums[i]) {
                        int temp = nums[p];
                        nums[p] = nums[i];
                        nums[i] = temp;
                        break;
                    }
                    p ++;
                }
                break;
            }
        }
        if (!changed) {
            // 递减
            Arrays.sort(nums);
        }
    }
}
