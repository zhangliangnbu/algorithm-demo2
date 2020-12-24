package com.liang.algo.window;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams obj = new FindAnagrams();
        System.out.println(obj.findAnagrams("cbaebabacd", "abc"));
        System.out.println(obj.findAnagrams("abab", "ab"));
        System.out.println(obj.findAnagrams("baa", "aa"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() < p.length()) {
            return new ArrayList<>();
        }

        // 滑动窗口方法
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = 0, validCount = 0;
        // 目标
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < p.length(); i ++) {
            char c = p.charAt(i);
            need.put(c, need.get(c) == null ? 1 : need.get(c) + 1);
        }
        // 窗口
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            // 扩大窗口
            char cr = s.charAt(right);
            right ++;
            if (need.containsKey(cr)) {
                window.put(cr, window.get(cr) == null ? 1 : window.get(cr) + 1);
                if (need.get(cr).equals(window.get(cr))) {
                    validCount ++;
                }
            }

            // 缩小窗口
            while (validCount == need.size()) {
               if (right - left == p.length()) {
                   ans.add(left);
               }
               char cl = s.charAt(left);
               left ++;
               if (need.containsKey(cl)) {
                   if (need.get(cl).equals(window.get(cl))) {
                       validCount --;
                   }
                   window.put(cl, window.get(cl) - 1);
               }
            }
        }

        return ans;

    }

}
