package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets {

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return lists;
        }

        // 一般决策树的变体，由多个决策树组成，决策路径个数范围为0~nums.length
        // 选择列表 nums
        // 选择路径 存储nums index
        LinkedList<Integer> track = new LinkedList<>();
        // 迭代回溯
        for (int i = 0, len = nums.length; i <= len; i ++) {
            // 回溯
            backtrack(nums, track, i);
        }

        // res
        return lists;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, int dpt) {
        if (track.size() == dpt) {
            lists.add(buildSubSet(nums, track));
            return;
        }

        // 限制 只能往后选不能往前选
        int startIndex = track.size() == 0 ? 0 : track.getLast() + 1;
        for (int i = startIndex, len = nums.length; i < len; i ++) {
            track.add(i);
            backtrack(nums, track, dpt);
            track.removeLast();
        }
    }

    private List<Integer> buildSubSet(int[] nums, LinkedList<Integer> track) {
        List<Integer> sub = new ArrayList<>();
        for (Integer i : track) {
            sub.add(nums[i]);
        }
        return sub;
    }



}
