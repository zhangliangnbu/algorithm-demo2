package com.liang.algo.greedy;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 */
public class Jump {
    public int jump(int[] nums) {
        // 总能到达最后一个位置，其实就是能够达到任意一个位置
        // 反证法：如果不能到达某一个位置，那么这个位置后的任意位置都需要更长的步数，也就是说也不能够到达
        // 动态规划：f(n) = Math.min(f(n+(1..a[n]))) + 1
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int[] dps = new int[n];
        dps[n - 1] = 0;
        for (int i = n - 2; i >= 0; i --) {
            int h = Math.min(nums[i], n-1-i);
            dps[i] = dps[i + 1] + 1;
            for (int j = 1; j <= h; j ++) {
                if (dps[i] > dps[i + j] + 1) {
                    dps[i] = dps[i + j] + 1;
                }
            }
        }
        return dps[0];
    }

    public int greedy(int[] nums) {
        // greedy: 找到比当前跳地更远的点
        int maxIndex = 0;
        int step = 0;
        for (int i = 1; i < nums.length - 1; i ++) {
            int m = i + nums[i];
            if (m > nums[maxIndex]) {
                maxIndex = m;
                step ++;
            }
            if (maxIndex >= nums.length - 1) {
                break;
            }
        }
        return step;
    }


}
