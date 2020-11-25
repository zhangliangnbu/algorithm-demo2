package com.liang.algo.backtrack;

import java.util.LinkedList;

public class CountNumbersWithUniqueDigits {

    private int count = 0;
    public int countNumbersWithUniqueDigits(int n) {
        // 路径列表
        LinkedList<Integer> track = new LinkedList<>();
        // 选择列表 0-9
        // 限制条件 路径列表各个数字不相同
        // 递归
        backtrack(n, track);
        return count;
    }

    private void backtrack(int n, LinkedList<Integer> track) {
        count ++;
        if (track.size() == n) {
            return;
        }

        for (int i = 0; i <= 9; i ++) {
            if (track.size() == 0 && i == 0) {
                continue;
            }
            if (track.contains(i)) {
                continue;
            }

            track.add(i);
            backtrack(n, track);
            track.removeLast();
        }
    }
}
