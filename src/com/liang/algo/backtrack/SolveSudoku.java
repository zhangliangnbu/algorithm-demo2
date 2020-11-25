package com.liang.algo.backtrack;

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 */
public class SolveSudoku {

    public static void main(String[] args) {
        System.out.println(Character.forDigit(9, 10));
    }

    private static final int N = 9;
    public void solveSudoku(char[][] board) {
        // 选择列表：1~9
        // 路径列表 board
        // 回溯

        int[] rc = {0, 0};
        backtrack(board, getNextBlank(board, rc));
    }

    // start 开始格子索引
    private boolean backtrack(char[][] board, int[] next) {
        // board填满 返回
        if (next == null) {
            return true;
        }

        for (int i = 1; i <= 9; i ++) {
            // 判断i是否有效
            if (!isValid(board, next, i)) {
                continue;
            }
            // 有效则添加
            board[next[0]][next[1]] = Character.forDigit(i, 10);
            // 继续选择
            boolean find = backtrack(board, getNextBlank(board, next));
            if (find) {
                return true;
            }
            // 回退 删除前面添加的格子
            board[next[0]][next[1]] = '.';
        }
        return false;
    }

    private int[] getNextBlank(char[][] board, int[] rc) {
        int[] next = new int[2];
        for (int i = rc[0]; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                if (board[i][j] == '.') {
                    next[0] = i;
                    next[1] = j;
                    return next;
                }
            }
        }
        return null;
    }

    private boolean isValid(char[][] board, int[] rc, int val) {
        int row = rc[0];
        int col = rc[1];
        // 行内重复
        for (int i = 0; i < N; i++) {
            if (board[row][i] == Character.forDigit(val, 10)) {
                return false;
            }
        }

        // 列重复
        for (int i = 0; i < N; i++) {
            if (board[i][col] == Character.forDigit(val, 10)) {
                return false;
            }
        }

        // 小九宫重复
        int smallRowStart = (row / 3) * 3;
        int smallColStart = (col / 3) * 3;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                if (board[i + smallRowStart][j + smallColStart] == Character.forDigit(val, 10)) {
                    return false;
                }
            }
        }

        return true;
    }
}
