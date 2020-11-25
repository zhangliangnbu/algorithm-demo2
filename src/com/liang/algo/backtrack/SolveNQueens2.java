package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 *
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class SolveNQueens2 {

    private int count = 0;
    public int totalNQueens(int n) {
        // 决策树模型：某层某点表示棋盘某行某列放置棋子
        // 路径 其中每个元素表示每行皇后的位置
        LinkedList<Integer> tracks = new LinkedList<>();
        // 选择列表 [0~n-1]
        backtrace(n, tracks);
        return count;
    }

    private void backtrace(int n, LinkedList<Integer> tracks) {
        if (tracks.size() == n) {
            count ++;
            return;
        }
        for (int i = 0; i < n; i ++) {
            // invalid
            if (!isValid(n, tracks, i)) {
                continue;
            }
            // valid
            tracks.add(i);
            backtrace(n, tracks);
            tracks.removeLast();
        }
    }

    private boolean isValid(int n, LinkedList<Integer> tracks, int col) {
        // 列不冲突
        for (int s : tracks) {
            if (s == col) {
                return false;
            }
        }

        // 左上不冲突
        for (int i = tracks.size() - 1, j = col - 1; i >= 0 && j >= 0; i --, j--) {
            if (tracks.get(i) == j){
                return false;
            }
        }

        // 右上不冲突
        for (int i = tracks.size() - 1, j = col + 1; i >= 0 && j < n; i --, j++) {
            if (tracks.get(i) == j) {
                return false;
            }
        }

        return true;
    }

}
