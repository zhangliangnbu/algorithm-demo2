package com.liang.algo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 * 通过次数132,757提交次数132,757
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        int target = -11;
        FourSum fs = new FourSum();
        fs.fourSum(nums, target);
    }

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 回溯
        if (nums == null || nums.length < 4) {
            return lists;
        }
        Arrays.sort(nums);
        // 选择路径
        LinkedList<Integer> track = new LinkedList<>();
        // 递归
        backtrack(nums, 0, track, target);
        return lists;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track, int target) {
        if (track.size() == 4) {
            if (target == 0) {
                lists.add(new ArrayList<>(track));
            }
            return;
        }
        for (int i = start;i < nums.length; i ++) {
            if (i != start && nums[i] == nums[i-1]) {
                continue;
            }
            // 剪枝
            if (target > 0 && nums[i] > target) {
                break;
            }
            track.add(nums[i]);
            backtrack(nums, i + 1, track, target - nums[i]);
            track.removeLast();
        }
    }
}
