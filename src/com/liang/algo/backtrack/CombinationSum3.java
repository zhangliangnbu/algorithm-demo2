package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 选择列表 0 ~ 9 限制条件：已经选择的不能再选
        // 路径列表, 辅助变量：路径和sum
        LinkedList<Integer> track = new LinkedList<>();
        // 限制条件 k
        // 回溯
        backtrack(k, track, n, 1);
        return lists;
    }

    private void backtrack(int k, LinkedList<Integer> track, int target, int startIndex) {
        if (track.size() == k) {
            if (target == 0) {
                lists.add(new ArrayList<>(track));
            }
            return;
        }

        // 不能往回选
        for (int i = startIndex; i <= 9; i ++) {
            if (target - i < 0) {
                return;
            }
            track.add(i);
            backtrack(k, track, target - i, i + 1);
            track.removeLast();
        }
    }
}
