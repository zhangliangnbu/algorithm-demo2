package com.liang.algo;

/**
 * 861. 翻转矩阵后的得分
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 *
 *
 * 示例：
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 */
public class MatrixScore {

    public static void main(String[] args) {
        int a = 1, b = 0;
        System.out.println("~1 = " + ~a);
        System.out.println("~0 = " + ~b);
    }

    public int matrixScore(int[][] A) {
        // 逐步保证高位1的数量为最大值

        // 保证最高位全部是1
        int row = A.length;
        int col = A[0].length;
        for (int i = 0; i < row; i ++) {
            if (A[i][0] == 0) {
                move(A, i, true);
            }
        }

        // 从高位到低位遍历 通过移动列 保证每列1的数量最大
        for (int i = 1; i < col; i ++) {
            if (countColOne(A, i) >= row / 2 + row % 2) {
                continue;
            }
            move(A, i, false);
        }

        // 计算
        int sum = 0;
        for (int i = 0; i < col; i ++) {
            sum += (countColOne(A, i) << (col - 1 - i));
        }

        return sum;
    }


    // 计算col列1的数量
    private int countColOne(int[][] A, int col) {
        int count = 0;
        for (int[] ints : A) {
            if (ints[col] == 1) {
                count++;
            }
        }
        return count;
    }

    private void move(int[][] A, int target, boolean isRow) {
        if (isRow) {
            for (int i = 0; i < A[target].length; i ++) {
                A[target][i] = not(A[target][i]);
            }
        } else {
            for (int i = 0; i < A.length; i ++) {
                A[i][target] = not(A[i][target]);
            }
        }
    }

    private int not(int a) {
        if (a == 0) {
            return 1;
        } else {
            return 0;
        }
    }


}
