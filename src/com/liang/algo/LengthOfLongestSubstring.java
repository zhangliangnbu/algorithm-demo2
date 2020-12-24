package com.liang.algo;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring obj = new LengthOfLongestSubstring();
        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.lengthOfLongestSubstring("bbbbb"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println(obj.lengthOfLongestSubstring(" "));
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 滑动窗口
        int left = 0, right = 0, maxLen = 0;
        // 窗口
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            right ++;
            window.put(c, window.get(c) == null ? 1 : window.get(c) + 1);
            while (window.get(c) != 1) {
                char cl = s.charAt(left);
                left ++;
                window.put(cl, window.get(cl) - 1);
            }
            maxLen = Math.max(right - left, maxLen);
        }

        return maxLen;
    }
}
