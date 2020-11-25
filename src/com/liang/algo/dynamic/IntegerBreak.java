package com.liang.algo.dynamic;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak2(n));
    }

    public static int integerBreak(int n) {
        // 状态转移方程：n时最大乘积 dps[n] = max(max(dps[k], k)*max(dps[n-k], n-k), 其中k=1...n/2)
        // 时间复杂度 O(n^2), 空间复杂度O(n)
        int[] dps = new int[n + 1];
        dps[0] = 1;
        dps[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j ++) {
                // 拆分的第一个值：本身或继续拆分的值
                int first = Math.max(j, dps[j]);
                // 拆分的第二个值：本身或继续拆分的值
                int second = Math.max(i - j, dps[i - j]);
                dps[i] = Math.max(dps[i], first * second);
            }
        }

        return dps[n];
    }

    public static int integerBreak2(int n) {
        // 状态转移方程：n时最大乘积 dps[n] = max(k * max(n-k, dps[n-k])), 其中k=1...n-1)
        // 时间复杂度 O(n^2), 空间复杂度O(n)
        if (n < 4) {
            return n - 1;
        }
        int[] dps = new int[n + 1];
        dps[0] = 0;
        dps[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j ++) {
                dps[i] = Math.max(dps[i], j * Math.max(i - j, dps[i-j]));
            }
        }

        return dps[n];
    }
}



















