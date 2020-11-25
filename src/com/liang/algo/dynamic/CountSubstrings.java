package com.liang.algo.dynamic;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 *
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class CountSubstrings {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }

    private static int countSubstrings(String s) {
        // 转移方程：f(n) = f(n-1) + 以第n个字符结尾的所有回文子串数目E(d)
        // 辅助矩阵d：i到j字符串是否为回文串d[i][j] = d[i+1][j-1] && s[i] == s[j]

        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        // i-j子串是否为回文串, 0否，1是
        int[][] dps = new int[n][n];

        int count = 0;
        for (int j = 0; j < n; j ++){
            // 以j字符结尾的回文串数目
            int endCount = 0;
            for (int i = 0; i <= j; i ++) {
                boolean se = s.charAt(i) == s.charAt(j);
                if (j - i >= 2) {
                    dps[i][j] = (dps[i + 1][j - 1] == 1) && se ? 1 : 0;
                } else {
                    dps[i][j] = se ? 1 : 0;
                }
                endCount += dps[i][j];
            }
            // 转移方程
            count += endCount;
        }
        return count;
    }
}
