package com.liang.algo.dynamic;

/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 *
 * 提示:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
//        String text1 = "abc";
//        String text2 = "abc";

//        String text1 = "abcde";
//        String text2 = "ace";

        String text1 = "abc";
        String text2 = "def";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        // 状态转移方程：dps[i][j] = max(dps[i][j-1], dps[i-1][j] or dps[i-1][j-1] + 1)
        // 说明：dps[i][j], 长度分别为i+1和j+1的字符串text1和text2的最长公共子序列长度
        // 说明：dps[i][j-1], 不以text2[j]结尾的公共子序列最大长度
        // 说明：dps[i-1][j] or dps[i-1][j-1] + 1, 以text2[j]结尾的公共子序列最大长度，以text1[i] == text2[j]的值分两情况
        // 时间复杂度O(n^2), 空间复杂度(O^2)

        int m = text1.length();
        int n = text2.length();
        int[][] dps = new int[m][n];

        // 递推
        int len1, len2;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                // 不以text2[j]结尾的公共子序列最大长度
                len1 = j - 1 >= 0 ? dps[i][j-1] : 0;
                // 以text2[j]结尾的公共子序列最大长度
                if (text1.charAt(i) == text2.charAt(j)) {
                    len2 = (i - 1 >= 0 && j - 1 >= 0) ? dps[i-1][j-1] + 1 : 1;
                } else {
                    len2 = i - 1 >= 0? dps[i-1][j] : 0;
                }

                dps[i][j] = Math.max(len1, len2);
            }
        }

        return dps[m-1][n-1];
    }


}
