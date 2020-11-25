package com.liang.algo.common;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        LengthOfLIS lis = new LengthOfLIS();
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(lis.lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        // 动态规划
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 以i为终点的最长子序列长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        // 0-i内的最长子序列长度
        int[] dp2 = new int[n];
        Arrays.fill(dp2, 1);
        for (int i = 1; i < n; i ++) {
            // 计算dp[i]
            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else if (nums[i] == nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp2[i] = Math.max(dp2[i-1], dp[i]);
        }
        return dp2[n-1];
    }
}
