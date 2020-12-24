package com.liang.algo.window;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 先一直扩大窗口，满足条件后开始缩小窗口
 */
public class MinWindow {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinWindow obj = new MinWindow();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int left = 0, right = 0, minLeft = 0, minRight = Integer.MAX_VALUE;
        // 目标范围
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            char c = t.charAt(i);
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }
        // 滑动窗口
        Map<Character, Integer> window = new HashMap<>();
        // valid
        int validCount = 0;

        while (right < s.length()) {
            // 扩大窗口
            char c = s.charAt(right);
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.get(c) == null ? 1 : window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) {
                    validCount ++;
                }
            }

            // 缩小窗口
            while (validCount == need.size()) {
                // 更新长度
                if (right - left < minRight - minLeft) {
                    minLeft = left;
                    minRight = right;
                }
                char cc = s.charAt(left);
                left ++;
                if (need.containsKey(cc)) {
                    if (Objects.equals(window.get(cc), need.get(cc))) {
                        validCount --;
                    }
                    window.put(cc, window.get(cc) - 1);
                }
            }
        }

        return minRight == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight);
    }

}
