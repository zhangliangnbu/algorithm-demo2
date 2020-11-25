package com.liang.algo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        // 方法一：hash表存储并遍历
        // 方法二：排序并遍历

        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        // count
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
           char c = s.charAt(i);
           int count = map.getOrDefault(c, 0);
           if (count == 0) {
               map.put(c, 1);
           } else {
               map.put(c, 0);
               max += 2;
           }
        }

        if (map.containsValue(1)) {
            max ++;
        }

        return max;
    }
}
