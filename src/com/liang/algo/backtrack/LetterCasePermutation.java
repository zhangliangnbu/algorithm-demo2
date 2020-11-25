package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 *
 *
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 *
 * 输入：S = "12345"
 * 输出：["12345"]
 */
public class LetterCasePermutation {
    private List<String> list = new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        if (S == null) {
            return null;
        }
        // 路径列表
        StringBuilder track = new StringBuilder();
        // 选择列表 每次选择S中一个字符 数字或大写字母或小写字母
        // 递归
        backtrack(S, track);
        return list;
    }

    private void backtrack(String S, StringBuilder track) {
        if (track.length() == S.length()) {
            list.add(new String(track));
            return;
        }

        int index = track.length();
        char c = S.charAt(index);
        if (c >= '0' && c <= '9') {
            track.append(c);
            backtrack(S, track);
            track.deleteCharAt(track.length() - 1);
        } else {
            track.append(Character.toLowerCase(c));
            backtrack(S, track);
            track.deleteCharAt(track.length() - 1);

            track.append(Character.toUpperCase(c));
            backtrack(S, track);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
