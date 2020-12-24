package com.liang.algo;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * 通过次数135,608提交次数209,350
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // 计数排序 比较
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 变形的排序
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i ++) {
                char c = str.charAt(i);
                count[c - 'a'] ++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i ++) {
                if (count[i] == 0) {
                    continue;
                }
                sb.append((char) (i + 'a'));
                sb.append(count[i]);
            }
            // 统计
            String sort = sb.toString();
            if (map.containsKey(sort)) {
                map.get(sort).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sort, list);
            }
        }

        return new ArrayList<>(map.values());
    }


}
