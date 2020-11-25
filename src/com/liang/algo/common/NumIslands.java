package com.liang.algo.common;

import java.util.Map;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * 通过次数170,652提交次数336,636
 */
public class NumIslands {

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (visited[i][j]) {
                    continue;
                }
                if (grid[i][j] == '1') {
                    count ++;
                    visit(grid, i, j, visited);
                }
            }
        }

        return count;
    }

    private void visit(char[][] grid, int row, int col, boolean[][] visited) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (grid[row][col] == '0') {
            return;
        }

        if (col > 0) {
            visit(grid, row, col - 1, visited);
        }
        if (row > 0) {
            visit(grid, row - 1, col, visited);
        }
        if (row < grid.length - 1) {
            visit(grid, row + 1, col, visited);
        }
        if (col < grid[row].length - 1) {
            visit(grid, row, col + 1, visited);
        }
    }


}
