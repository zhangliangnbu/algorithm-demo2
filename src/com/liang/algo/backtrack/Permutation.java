package com.liang.algo.backtrack;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 */
public class Permutation {
    private List<List<Character>> lists = new ArrayList<>();

    public String[] permutation(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return new String[0];
        }

        // 选择列表: 去重的nums - 已经选完的数字; 使用map 记录重复数字的数量 key-数字，value-重复数量
        Map<Character, Integer> map = buildMap(s);
        // 路径列表
        LinkedList<Character> track = new LinkedList<>();
        // 回溯
        backtrack(map, track, s.length());
        return buildStrArr(lists);
    }

    private void backtrack(Map<Character, Integer> map, LinkedList<Character> track, int len) {
        if (track.size() == len) {
            lists.add(new ArrayList<>(track));
            return;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            map.put(entry.getKey(), entry.getValue() - 1);
            track.add(entry.getKey());
            backtrack(map, track, len);
            Character key = track.removeLast();
            map.put(key, map.get(key) + 1);
        }
    }

    private String[] buildStrArr(List<List<Character>> lists) {
        String[] strArr = new String[lists.size()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = lists.size(); i < size; i ++) {
            if (sb.length() > 0) {
                sb.delete(0, sb.length());
            }
            for (int j = 0, js = lists.get(i).size(); j < js; j ++) {
                sb.append(lists.get(i).get(j));
            }
            strArr[i] = sb.toString();
        }
        return strArr;
    }

    private Map<Character, Integer> buildMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }
}
