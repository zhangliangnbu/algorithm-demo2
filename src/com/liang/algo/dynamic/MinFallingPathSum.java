package com.liang.algo.dynamic;

/**
 * 931. 下降路径最小和
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 *
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 *
 *
 *
 * 示例：
 *
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12。
 *
 *
 *
 * 提示：
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 */
public class MinFallingPathSum {
    public static void main(String[] args) {
//        int[][] A = {{1,2,3},{4,5,6},{7,8,9}};
        // [[10,-98,44],[-20,65,34],[-100,-1,74]]
        int[][] A = {{10,-98,44},{-20,65,34},{-100,-1,74}};
        System.out.println(minFallingPathSum(A));
    }

    public static int minFallingPathSum(int[][] A) {
        // 转移方程：(i,j)处的最小路径和 dps[i][j] = min(dps[i-1][j-1], dps[i-1][j], dps[i-1][j+1]) + A[i][j]
        // 最终结果：min(dps[n-1][0], ..., dps[n-1][n-1])
        // 时间复杂度O(n^2), 空间复杂度O(n)
        int n = A.length;
        // 第一行最小路径和
        int[] dp1 = new int[n];
        System.arraycopy(A[0], 0, dp1, 0, n);
        // 第二行最小路径和
        int[] dp2 = new int[n];

        int s1, s2, s3;
        int[] temp;
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                // 转移
                s1 = j - 1 < 0 ? Integer.MAX_VALUE : dp1[j - 1];
                s2 = dp1[j];
                s3 = j + 1 > n - 1 ? Integer.MAX_VALUE : dp1[j + 1];
                dp2[j] = Math.min(s1, Math.min(s2, s3)) + A[i][j];
            }
            // 计算下一行之前，抛弃上一行无用的数据，指向当前行
            temp = dp1;
            dp1 = dp2;
            dp2 = temp;
        }

        // 最终结果
        int min = dp1[0];
        for (int i = 0; i < n; i ++) {
            min = Math.min(min, dp1[i]);
        }
        return min;
    }
}
