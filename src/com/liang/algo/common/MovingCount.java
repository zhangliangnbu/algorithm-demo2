package com.liang.algo.common;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 */
public class MovingCount {
    // count
    private int count = 0;
    public int movingCount(int m, int n, int k) {
        // 四叉树
        // 是否访问过
        boolean[][] visited = new boolean[m][n];

        // 递归
        dc(m, n, k, visited, 0, 0);
        return count;
    }

    private void dc(int m, int n, int k, boolean[][] visited, int row, int col) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (digitSum(row) + digitSum(col) > k) {
            return;
        }
        count ++;
        if (row > 0) {
            dc(m, n, k, visited, row - 1, col);
        }
        if (row < m - 1) {
            dc(m, n, k, visited, row + 1, col);
        }
        if (col > 0) {
            dc(m, n, k, visited, row, col - 1);
        }
        if (col < n - 1) {
            dc(m, n, k, visited, row, col + 1);
        }
    }

    private int digitSum(int d) {
        int sum = 0;
        while (d > 0) {
            sum += d % 10;
            d = d / 10;
        }
        return sum;
    }
}
