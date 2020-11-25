package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 通过次数53,116提交次数86,757
 */
public class SubSets3 {

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null) {
            return lists;
        }

        // 选择列表 nums
        Arrays.sort(nums);
        // 选择路径 存储nums值
        LinkedList<Integer> track = new LinkedList<>();
        // 回溯
        backtrack(nums, track, 0);
        return lists;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, int startIndex) {
        // 决策点操作：每次做决策的时候 先存储 即在每个决策点先存储
        lists.add(new ArrayList<>(track));

        // 进行选择：限制 只能往后选不能往前选
        for (int i = startIndex, len = nums.length; i < len; i ++) {
            // 每次做选择时，重复元素只选择一次
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track, i + 1);
            track.removeLast();
        }
    }

}
