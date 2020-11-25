package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combine {
    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 选择列表 1 ~ n 限制条件：已经选择的不能再选
        // 路径列表
        LinkedList<Integer> track = new LinkedList<>();
        // 限制条件 k
        // 回溯
        backtrack(n, track, k, 1);
        return lists;
    }

    private void backtrack(int n, LinkedList<Integer> track, int k, int startIndex) {
        if (track.size() == k) {
            lists.add(new ArrayList<>(track));
            return;
        }

        // 不能往回选
        for (int i = startIndex; i <= n; i ++) {
            track.add(i);
            backtrack(n, track, k, i + 1);
            track.removeLast();
        }
    }
}
