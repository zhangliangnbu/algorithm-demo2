package com.liang.algo.dynamic;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
//        int[] nums = {1,1,-1,-1,3};
        int[] nums = {0,2,1,-3};
        int target = 1;
        ThreeSumClosest tsc = new ThreeSumClosest();
        System.out.println(tsc.threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i ++) {
            int p = i + 1, q = nums.length - 1;
            while (p < q) {
                int ts = nums[i] + nums[p] + nums[q];
                if (ts - target == 0) {
                    return ts;
                } else {
                    if (ts > target) {
                        q --;
                    } else {
                        p ++;
                    }
                    if (Math.abs(ts - target) < Math.abs(sum - target)) {
                        sum = ts;
                    }
                }
            }
        }
        return sum;
    }
}
