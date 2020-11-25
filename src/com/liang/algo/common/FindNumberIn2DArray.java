package com.liang.algo.common;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 */
public class FindNumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 递归
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        return find(matrix, 0, 0, col - 1, row - 1, target);
    }

    private boolean find(int[][] matrix, int l, int t, int r, int b, int target) {
        if (l > r || t > b) {
            return false;
        }
        if (target < matrix[t][l] || target > matrix[b][r]) {
            return false;
        }
        if (target == matrix[t][l] || target == matrix[b][r]) {
            return true;
        }
        int mRow = (t + b) / 2;
        int mCol = (l + r) / 2;
        if (matrix[mRow][mCol] == target) {
            return true;
        }

        boolean lt = find(matrix, l, t, mCol, mRow, target);
        boolean tr = find(matrix, mCol + 1, t, r, mRow, target);
        boolean rb = find(matrix, mCol + 1, mRow + 1, r, b, target);
        boolean bl = find(matrix, l, mRow + 1, mCol, b, target);
        return lt || tr || rb || bl;
    }

}
