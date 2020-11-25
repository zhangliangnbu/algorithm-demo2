package com.liang.algo.dynamic;

/**
 * 516. 最长回文子序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 *
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {
//        String s = "cbbd";
//        String s = "bbbab";
        String s = "bbcabbcabbccbabbbccbab";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int my1(String s) {
        // 状态转移方程：i~j间的最长回文子序列长度 dps[i][j] = max(dps[i][j-1], 以j结尾的最长回文长度)
        // 以j结尾的最长回文长度 = dps[k+1][j-1] + 2 or 2 or 1, 其中k为s[j]字符在i~j之间出现的位置，为j的话长度为1
        // 由于dps[i][j] >= dps[i+1][j] >= 以j结尾的最长回文长度(s[i]!=s[j]时)，所以状态转移方程可变形为：
        // dps[i][j] = max(dps[i][j-1], dps[i+1][j](s[i]!=s[j]的情况) or dps[i+1][j-1] + 2 （s[i]==s[j]的情况）)
        // 时间复杂度O(n^2)，空间复杂度O(n^2)

        int n = s.length();
        // i~j间的最长回文子序列长度
        int[][] dps = new int[n][n];

        // 初始化
        for (int i = 0; i < n; i ++) {
            dps[i][i] = 1;
        }

        // 递推
        for (int i = n - 1; i >= 0; i --) {
            for (int j = i + 1; j < n; j ++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dps[i][j] = Math.max(dps[i][j-1], (i == j - 1 ? 0 : dps[i+1][j-1]) + 2);
                } else {
                    dps[i][j] = Math.max(dps[i][j-1], dps[i+1][j]);
                }
            }
        }

        return dps[0][n-1];
    }

    public static int longestPalindromeSubseq(String s) {
        // 状态转移方程：i~j间的最长回文子序列长度 dps[i][j] = max(dps[i][j-1], 以j结尾的最长回文长度)
        // 以j结尾的最长回文长度 = dps[k+1][j-1] + 2 or 2 or 1, 其中k为s[j]字符在i~j之间出现的位置，找不到的话长度为1
        // 时间复杂度O(n^2)，空间复杂度O(n^2)

        int n = s.length();
        // i~j间的最长回文子序列长度
        int[][] dps = new int[n][n];
        // i~j间的s[j]首次出现的位置
        int[][] ks = new int[n][n];

        // 初始化
        for (int i = 0; i < n; i ++) {
            dps[i][i] = 1;
            for (int j = i; j < n; j ++) {
                ks[i][j] = j;
            }
        }

        // 递推
        int k;
        for (int i = n - 1; i >= 0; i --) {
            for (int j = i + 1; j < n; j ++) {
                ks[i][j] = s.charAt(i) == s.charAt(j) ? i : ks[i+1][j];
                k = ks[i][j];
                if (k == j) {
                    dps[i][j] = Math.max(dps[i][j-1], 1);
                } else if (k == j - 1) {
                    dps[i][j] = Math.max(dps[i][j-1], 2);
                } else if (k < j - 1) {
                    dps[i][j] = Math.max(dps[i][j-1], dps[k+1][j-1] + 2);
                }
            }
        }

        return dps[0][n-1];
    }



}
