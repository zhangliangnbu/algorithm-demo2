package com.liang.algo.common;

import java.util.LinkedList;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * 通过次数163,430提交次数396,491
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        // 回溯
        if (nums == null || nums.length <= 1) {
            return true;
        }

        // 选择列表：第i个决策的选择列表为i+1 ~ i+nums[i]
        // 决策路径 存储索引值
        LinkedList<Integer> track = new LinkedList<>();
        // 访问限制
        boolean[] visited = new boolean[nums.length];
        // 回溯选择
        return backtrack(nums, track, visited, 0);
    }

    private boolean backtrack(int[] nums, LinkedList<Integer> track, boolean[] visited, int start) {
        if (track.size() > 0 && track.getLast() == nums.length - 1) {
            return true;
        }
        for (int i = start + 1; i <= Math.min(start + nums[start], nums.length - 1); i ++) {
            if (visited[i]) {
                continue;
            }
            track.add(i);
            visited[i] = true;
            if (backtrack(nums, track, visited, i)) {
                return true;
            }
            track.removeLast();
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        // 最远距离
        if (nums == null || nums.length <= 1) {
            return true;
        }

        int maxIndex = nums[0];
        for (int i = 1; i <= nums.length - 1; i ++) {
            if (i == nums.length - 1 && i <= maxIndex) {
                return true;
            }
            if (i <= maxIndex) {
                maxIndex = Math.max(maxIndex, i + nums[i]);
            }
        }
        return false;
    }
}
