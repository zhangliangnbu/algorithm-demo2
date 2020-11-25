package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class LetterCombinations {
    public static void main(String[] args) {
        System.out.println((char)97); // a
        new LetterCombinations().letterCombinations("7");
    }

    private List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        // 选择列表
        int[] choices = buildChoices(digits);
        if (choices == null || choices.length == 0) {
            return list;
        }
        // 路径
        StringBuilder sb = new StringBuilder();
        // 回溯
        backtrace(choices, 0, sb);
        return list;
    }

    private void backtrace(int[] choices, int level, StringBuilder sb) {
        if (sb.length() == choices.length) {
            list.add(new String(sb));
            return;
        }
        int num = choices[level];
        for (int i = 0; i < choiceSize(num); i ++) {
            // valid
            sb.append(buildChar(num, i));
            backtrace(choices, level + 1, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }

    private char buildChar(int num, int index) {
        if (num >= 2 && num <= 7) {
            return (char)((num - 2) * 3 + 97 + index);
        } else {
            return (char)((num - 2) * 3 + 1 + 97 + index);
        }
    }
    private int choiceSize(int num) {
        if (num == 7 || num == 9) {
            return 4;
        }
        return 3;
    }

    private int[] buildChoices(String digits) {
        if (digits == null || digits.length() == 0) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0, len = digits.length() ; i < len; i ++) {
            char c = digits.charAt(i);
            if (c >= '2' && c <= '9') {
                list.add(Character.digit(c, 10));
            }
        }

        int[] choices = new int[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            choices[i] = list.get(i);
        }

        return choices;
    }

}
