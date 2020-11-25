package com.liang.algo.divide;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
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
 */
public class SearchMatrix {

    // [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]
    //19
    public static void main(String[] args) {
//        int[][] mat = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
//        int[][] mat = {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}};
        int[][] mat = {{1, 1}};
        SearchMatrix sm = new SearchMatrix();
        System.out.println(sm.searchMatrix(mat, 1));
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        // 二分查找
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean a = dc(matrix, 0, 0, col - 1, row - 1, target);
        return a;
    }

    private boolean dc(int[][] matrix, int left, int top, int right, int bottom, int target) {
        if (left > right || top > bottom) {
            return false;
        }

        if (target < matrix[top][left] || target > matrix[bottom][right]) {
            return false;
        }

        // 二分
        int col = (left + right) / 2;
        int row = top;
        while (row <= bottom && matrix[row][col] <= target) {
            if (target == matrix[row][col]) {
                return true;
            }
            row ++;
        }

        boolean lb = dc(matrix, left, row, col - 1, bottom, target);
        boolean rt = dc(matrix, col + 1, top, right, row - 1, target);
        return lb || rt;
    }
}
