package com.liang.algo.dynamic;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * 通过次数87,965提交次数152,011
 */
public class NumSquares {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(numSquares(n));
    }

    public static int my1(int n) {
        // 状态转移方程：n时的最小平方个数 dps[n] = min(dps[k] + dps[n-k], k = 1...n / 2) or 1 (if n 是平方数)
        // 时间复杂度O(n^2), 空间复杂度O(n)
        int[] dps = new int[n + 1];

        // 初始化
        for (int i = 1; i <= n; i ++) {
            dps[i] = i;
        }

        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= i / 2; j ++) {
                if (i == j * j) {
                    dps[i] = 1;
                    break;
                }
                dps[i] = Math.min(dps[i], dps[j] + dps[i - j]);
            }
        }

        return dps[n];
    }

    public static int numSquares(int n) {
        // 状态转移方程：i时的最小平方个数 dps[i] = Math.min(dps[i], 1 + dps[i - sqrts[j]], j = 1...sqrt(i)); or 1 (if n 是平方数)
        // 时间复杂度O(n*sqrt(n)), 空间复杂度O(n)
        int[] dps = new int[n + 1];
        int[] sqrts = new int[n + 1];

        // 初始化
        for (int i = 1; i <= n; i ++) {
            dps[i] = i;
        }
        for (int i = 1; i <= n; i ++) {
            sqrts[i] = i * i;
        }

        for (int i = 1; i <= n; i ++) {
            int j = 1;
            while (i - sqrts[j] >= 0) {
                if (i == sqrts[j]) {
                    dps[i] = 1;
                    break;
                }
                dps[i] = Math.min(dps[i], 1 + dps[i - sqrts[j]]);
                j ++;
            }
        }

        return dps[n];
    }


}
