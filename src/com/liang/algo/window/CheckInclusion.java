package com.liang.algo.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class CheckInclusion {

    public static void main(String[] args) {
        CheckInclusion obj = new CheckInclusion();
        System.out.println(obj.checkInclusion("hello", "ooolleoooleh"));
        System.out.println(obj.checkInclusion("ab", "eidboaoo"));
        System.out.println(obj.checkInclusion("ab", "eidbaooo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s2 == null || s1 == null || s2.length() < s1.length()) {
            return false;
        }
        // 滑动窗口方法 修剪版本
        int left = 0, right = 0, validCount = 0;
        // 目标
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i ++) {
            char c = s1.charAt(i);
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }
        // 窗口
        Map<Character, Integer> window = new HashMap<>();

        while (right < s2.length()) {
            // 扩大窗口
            char c = s2.charAt(right);
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.get(c) == null ? 1 : window.get(c) + 1);
                if (need.get(c).equals(window.get(c))) {
                    validCount ++;
                }
            } else {
                window.clear();
                validCount = 0;
                left = right;
            }

            // 缩小窗口
            while (validCount == need.size()) {
                if (right - left == s1.length()) {
                    return true;
                }
                char cc = s2.charAt(left);
                left ++;
                if (need.get(cc).equals(window.get(cc))) {
                    validCount --;
                }
                window.put(cc, window.get(cc) - 1);
            }
        }

        return false;
    }
}
