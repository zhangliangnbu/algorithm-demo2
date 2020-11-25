package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * 通过次数112,452提交次数174,239
 */
public class CombinationSum2 {
    private List<List<Integer>> lists = new ArrayList<>();
    private int sum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 选择列表 candidates
        Arrays.sort(candidates);
        // 路径列表, 辅助变量 路径和 sum
        LinkedList<Integer> track = new LinkedList<>();
        // 限制条件 target
        // 回溯
        backtrack(candidates, track, target, 0);
        return lists;
    }

    private void backtrack(int[] candidates, LinkedList<Integer> track, int target, int startIndex) {
        if (sum == target) {
            lists.add(new ArrayList<>(track));
            return;
        }
        // 不能往回选
        for (int i = startIndex; i < candidates.length; i ++) {
            int val = candidates[i];
            // 之前已经选择了 就不用选了
            if (i > startIndex && val == candidates[i - 1]) {
                continue;
            }
            if (sum + val > target) {
                continue;
            }
            track.add(val);
            sum += val;
            backtrack(candidates, track, target, i + 1);
            sum -= track.removeLast();
        }
    }
}
