package com.liang.algo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        // 遍历一半对角线点，一层一层剥离
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int min = Math.min(m, n);
        for (int i = 0; i < min / 2; i ++) {
            int row = i, col = i;
            // top
            while (col < (n - i) - 1) {
                list.add(matrix[row][col]);
                col ++;
            }
            // right
            while (row < (m - i) - 1) {
                list.add(matrix[row][col]);
                row ++;
            }
            // bottom
            while (col > i) {
                list.add(matrix[row][col]);
                col --;
            }
            // left
            while (row > i) {
                list.add(matrix[row][col]);
                row --;
            }
        }
        // last 一行或一列
        if (min % 2 != 0) {
            int i = min / 2;
            if (m >= n) {
                // 剩余列
                for (int j = i; j < m - i; j ++) {
                    list.add(matrix[j][i]);
                }
            } else {
                // 剩余行
                for (int j = i; j < n - i; j ++) {
                    list.add(matrix[i][j]);
                }
            }

        }

        return list;
    }

    public int[] spiralOrder2(int[][] matrix) {
        // 遍历一半对角线点，一层一层剥离
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] list = new int[m*n];
        int index = 0;
        int min = Math.min(m, n);
        for (int i = 0; i < min / 2; i ++) {
            int row = i, col = i;
            // top
            while (col < (n - i) - 1) {
                list[index] = matrix[row][col];
                index ++;
                col ++;
            }
            // right
            while (row < (m - i) - 1) {
                list[index] = matrix[row][col];
                index ++;
                row ++;
            }
            // bottom
            while (col > i) {
                list[index] = matrix[row][col];
                index ++;
                col --;
            }
            // left
            while (row > i) {
                list[index] = matrix[row][col];
                index ++;
                row --;
            }
        }
        // last 一行或一列
        if (min % 2 != 0) {
            int i = min / 2;
            if (m >= n) {
                // 剩余列
                for (int j = i; j < m - i; j ++) {
                    list[index] = matrix[j][i];
                    index ++;
                }
            } else {
                // 剩余行
                for (int j = i; j < n - i; j ++) {
                    list[index] = matrix[i][j];
                    index ++;
                }
            }

        }

        return list;
    }
}
