package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 对 SubSets的改进
// 决策树的叶子结点不需要深度一样
public class SubSets2 {

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return lists;
        }

        // 非定深决策树 需要变量进行控制
        // 选择列表 nums
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
            track.add(nums[i]);
            backtrack(nums, track, i + 1);
            track.removeLast();
        }
    }

}
