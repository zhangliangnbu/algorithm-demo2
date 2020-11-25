package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {

    private List<List<Integer>> lists = new ArrayList<>();
    private int sum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 选择列表 candidates
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
            if (sum + val > target) {
                continue;
            }
            track.add(val);
            sum += val;
            backtrack(candidates, track, target, i);
            sum -= track.removeLast();
        }
    }

}
