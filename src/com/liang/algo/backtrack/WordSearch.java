package com.liang.algo.backtrack;

import java.util.LinkedList;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * 通过次数118,527提交次数271,246
 * 在真实的面试中遇到过这道题？
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        if (row * col < word.length()) {
            return false;
        }
        LinkedList<int[]> track = new LinkedList<>();
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (board[i][j] == word.charAt(0)) {
                    track.clear();
                    track.add(new int[]{i, j});
                    if(backtrack(board, i, j, track, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int row, int col, LinkedList<int[]> track, String word) {
        if (track.size() == word.length()) {
            return true;
        }

        // left
        if (col > 0) {
            int nr = row, nc = col - 1;
            if (helper(board, nr, nc, track, word)) {
                return true;
            }
        }
        // right
        if (col < board[0].length - 1) {
            int nr = row, nc = col + 1;
            if (helper(board, nr, nc, track, word)) {
                return true;
            }
        }
        // top
        if (row > 0) {
            int nr = row - 1, nc = col;
            if (helper(board, nr, nc, track, word)) {
                return true;
            }
        }
        // bottom
        if (row < board.length - 1) {
            int nr = row + 1, nc = col;
            if (helper(board, nr, nc, track, word)) {
                return true;
            }
        }

        return false;
    }

    private boolean helper(char[][] board, int nr, int nc, LinkedList<int[]> track, String word) {
        if (board[nr][nc] == word.charAt(track.size()) && !contain(track, nr, nc)) {
            track.add(new int[]{nr, nc});
            if (backtrack(board, nr, nc, track, word)) {
                return true;
            }
            track.removeLast();
        }
        return false;
    }



    private boolean contain(LinkedList<int[]> track, int row, int col) {
        for (int[] a : track) {
            if (a[0] == row && a[1] == col) {
                return true;
            }
        }
        return false;
    }
}
