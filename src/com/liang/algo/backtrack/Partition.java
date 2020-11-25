package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Partition {

    private List<List<String>> lists = new ArrayList<>();
    private boolean[][] dps;
    public List<List<String>> partition(String s) {
        // 路径列表 元素为s中字符索引
        LinkedList<Integer> track = new LinkedList<>();
        // 选择列表 从track最后一个索引开始的字符集合
        // 辅助检查
        dps = buildValidDps(s);
        // 回溯
        backtrack(s, track);
        return lists;
    }

    private void backtrack(String s, LinkedList<Integer> track) {
        if (!track.isEmpty() && track.getLast() == s.length() - 1) {
            lists.add(buildStrList(s, track));
            return;
        }
        int start = track.isEmpty() ? 0 : track.getLast() + 1;
        for (int i = start, len = s.length(); i < len; i ++) {
            if (!dps[start][i]) {
                continue;
            }
            track.add(i);
            backtrack(s, track);
            track.removeLast();
        }
    }

    private List<String> buildStrList(String s, LinkedList<Integer> track) {
        List<String> list = new ArrayList<>();
        for (int i = 0, size = track.size(); i < size; i ++) {
            list.add(s.substring(i == 0 ? 0 : track.get(i - 1) + 1, track.get(i) + 1));
        }
        return list;
    }

    private boolean isValid(String s) {
        // 是否为回文
        int p = 0, q = s.length() - 1;
        while (p <= q) {
            if (s.charAt(p) != s.charAt(q)) {
                return false;
            }
            p ++;
            q --;
        }
        return true;
    }

    private boolean[][] buildValidDps(String s) {
        int n = s.length();
        boolean[][] dps = new boolean[n][n];
        for (int i = n - 1; i >=0; i --) {
            for (int j = i; j < n; j ++) {
                if (i == j) {
                    dps[i][j] = true;
                } else {
                    dps[i][j] = s.charAt(i) == s.charAt(j) && (j - i == 1 || dps[i + 1][j - 1]);
                }
            }
        }
        return dps;
    }
}
