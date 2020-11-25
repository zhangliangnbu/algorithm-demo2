package com.liang.algo.common;

import java.util.Stack;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        // 动态规划
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int n = s.length();
        // dps[i][j] 表示 以i开始，以j结束的有效括号长度， i < j
        int leftCount = 0, ijCount = 0, max = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n; j += 2) {
                if (j == i + 1) {
                    leftCount = 0;
                    ijCount = 0;
                }
                // i ~ j
                for (int k = j-1; k <= j; k ++) {
                    char c = s.charAt(k);
                    if (c == '(') {
                        leftCount ++;
                    } else {
                        if (leftCount <= 0) {
                            ijCount = 0;
                            break;
                        }
                        leftCount --;
                        ijCount += 2;
                    }
                }
                if (leftCount == 0) {
                    max = Math.max(max, ijCount);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses lvp = new LongestValidParentheses();
        String s = "(()";
//        String s = "()(()";
        System.out.println(lvp.longestValidParentheses2(s));
    }

    public int longestValidParentheses2(String s) {
        // 配对与重置
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int max = 0, temp = 0, leftCount = 0, st = 0;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount ++;
            } else {
                if (leftCount == 0) {
                    temp = 0;
                    st = 0;
                } else {
                    leftCount --;
                    temp += 2;
                    st += 2;
                    if (leftCount == 0) {
                        st = 0;
                        max = Math.max(temp, max);
                    }
                }
            }
        }
        max = Math.max(max, st);

        return max;
    }
}
