package com.liang.algo.common;

import java.util.LinkedList;
import java.util.List;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 说明：
 *
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 *
 * 示例 1：
 *
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 */
public class ReverseWords {

    public String reverseWords(String s) {
        LinkedList<Character> list = new LinkedList<>();
        int insert = 0;
        for (int i = s.length() - 1; i >= 0; i --) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (list.size() > 0 && list.getLast() != ' ') {
                    list.add(' ');
                }
                insert = list.size();
            } else {
                list.add(insert, c);
            }
        }

        if (list.getLast() == ' ') {
            list.removeLast();
        }

        char[] cs = new char[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            cs[i] = list.get(i);
        }
        return new String(cs);
    }

}
