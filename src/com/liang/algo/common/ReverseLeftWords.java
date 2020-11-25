package com.liang.algo.common;

public class ReverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() <= n) {
            return s;
        }
        String head = s.substring(n);
        String tail = s.substring(0, n);
        return head + tail;
    }
}
