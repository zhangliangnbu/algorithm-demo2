package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 60. 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class GetPermutation {

    public static void main(String[] args) {
        new GetPermutation().getPermutation(3, 3);
    }

    public String getPermutation(int n, int k) {
        // 选择列表 0 ~ n
        // 路径列表
        LinkedList<Integer> track = new LinkedList<>();

        int[] dps = new int[n + 1];
        dps[0] = 1;
        dps[1] = 1;
        for (int i = 2; i <= n; i ++) {
            dps[i] = i * dps[i - 1];
        }

        // 回溯
        backtrack(n, track, k, dps);
        // res
        StringBuilder sb = new StringBuilder();
        for (int i : track) {
            sb.append(i);
        }
        return sb.toString();
    }

    private void backtrack(int n, LinkedList<Integer> track, int k, int[] dps) {
        if (track.size() == n) {
            return;
        }
        // 剩余数字
        List<Integer> list = getResidue(n, track);
        for (int i = 0, size = list.size(); i < size; i ++) {
            int start = i * dps[size - 1];
            int end = (i + 1) * dps[size - 1];
            if (k <=  start || k > end) {
                continue;
            }

            track.add(list.get(i));
            backtrack(n, track, k - start,  dps);
            // 斩断回溯
            if (track.size() == n) {
                return;
            }
            track.removeLast();
        }
    }

    private List<Integer> getResidue(int n, LinkedList<Integer> track) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!track.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }

}
