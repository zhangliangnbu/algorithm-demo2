package com.liang.algo.backtrack;

import java.util.LinkedList;

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
public class GetPermutation2 {

    public static void main(String[] args) {
        new GetPermutation2().getPermutation(3, 1);
    }

    private int count = 0;
    public String getPermutation(int n, int k) {
        // 不用回溯
        int[] dps = new int[n + 1];
        dps[1] = 1;
        // cal dps
        for (int i = 2; i <= n; i ++) {
            dps[i] = i * dps[i - 1];
        }
        return null;

    }

}
