package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String[] args) {
        new GenerateParenthesis().generateParenthesis(2);
    }

    private List<String> list = new ArrayList<>();
    private int leftCount = 0, rightCount = 0;
    public List<String> generateParenthesis(int n) {
        // 选择列表: '(', ')'
        char[] choices = {'(', ')'};
        // 路径
        StringBuilder tracks = new StringBuilder();
        // 回溯
        backtrace(n, choices, tracks);
        return list;
    }

    private void backtrace(int n, char[] choices, StringBuilder tracks) {
        if (tracks.length() == 2 * n) {
            list.add(new String(tracks));
            return;
        }

        for (char choice : choices) {
            // invalid
            if (!isValid(n, choice, tracks)) {
                continue;
            }

            // valid
            tracks.append(choice);
            if (choice == '(') {
                leftCount ++;
            } else {
                rightCount ++;
            }

            backtrace(n, choices, tracks);

            char c = tracks.charAt(tracks.length() - 1);
            if (c == '(') {
                leftCount --;
            } else {
                rightCount --;
            }
            tracks.deleteCharAt(tracks.length() - 1);
        }
    }

    private boolean isValid(int n, char choice, StringBuilder tracks) {
        if (choice == '(') {
            // 左括号不能出现在最后
            if (tracks.length() == 2 * n - 1) {
                return false;
            }
            // 左括号数量不能超过n
            return leftCount < n;
        } else {
            // 右括号数量必须小于左括号
            if (leftCount <= rightCount) {
                return false;
            }
            // 右括号数量不能超过n
            return rightCount < n;
        }
    }
}
