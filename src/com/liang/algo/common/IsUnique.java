package com.liang.algo.common;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 01.01. 判定字符是否唯一
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class IsUnique {

    public boolean isUnique(String astr) {
        // 方法：排序遍历、利用Set
        if (astr == null) {
            return false;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i ++) {
            set.add(astr.charAt(i));
        }
        return astr.length() == set.size();
    }
}
