package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 通过次数205,710提交次数267,092
 */
public class Permute {

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> tracks = new LinkedList<>();
        backtrack(nums, tracks);
        return lists;
    }

    public void backtrack(int[] nums, LinkedList<Integer> tracks) {
        if (nums.length == tracks.size()) {
            lists.add(new ArrayList<>(tracks));
            return;
        }

        for (int num : nums) {
            if (tracks.contains(num)) {
                continue;
            }
            tracks.add(num);
            backtrack(nums, tracks);
            tracks.removeLast();
        }
    }
}
