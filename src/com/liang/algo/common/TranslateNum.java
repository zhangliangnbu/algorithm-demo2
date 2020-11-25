package com.liang.algo.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 */
public class TranslateNum {

    public static void main(String[] args) {
        TranslateNum tn = new TranslateNum();
        System.out.println(tn.translateNum(624));
    }

    private int count = 0;
    public int translateNum(int num) {


        // 回溯
        // 拆分
        LinkedList<Integer> choices = new LinkedList<>();
        while (num > 9) {
            choices.addFirst(num % 10);
            num = num / 10;
        }
        choices.addFirst(num);
        // 选择路径
        LinkedList<Integer> track = new LinkedList<>();
        // 递归
        backtrack(choices, track, 0);
        return count;
    }

    private void backtrack(List<Integer> choices, LinkedList<Integer> track, int start) {
        if (track.size() == choices.size()) {
            count ++;
            return;
        }

        // 每次两种选择
        // 选择1位
        track.add(start);
        backtrack(choices, track, start + 1);
        track.removeLast();

        // 选择两位
        if (start < choices.size() - 1
                && choices.get(start) != 0
                && choices.get(start) * 10 + choices.get(start + 1) <= 25) {
            track.add(start);
            track.add(start + 1);
            backtrack(choices, track, start + 2);
            track.removeLast();
            track.removeLast();
        }
    }

    public int translateNum2(int num) {
        // 动态规划
        // 拆分
        LinkedList<Integer> choices = new LinkedList<>();
        while (num > 9) {
            choices.addFirst(num % 10);
            num = num / 10;
        }
        choices.addFirst(num);

        int n = choices.size();

        int cp = 1, cc = 1;
        for (int i = 1; i < n; i ++) {
            int temp = choices.get(i-1) * 10 + choices.get(i);
            if (temp >= 10 && temp <= 25) {
                int ct = cc;
                cc = cc + cp;
                cp = ct;
            } else {
                cp = cc;
            }
        }
        return cc;
    }
}
