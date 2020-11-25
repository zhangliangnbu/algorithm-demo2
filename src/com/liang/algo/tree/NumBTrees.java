package com.liang.algo.tree;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 通过次数91,902提交次数132,841
 */
public class NumBTrees {

    public static void main(String[] args) {
        NumBTrees obj = new NumBTrees();
        System.out.println(obj.numTrees(4));
    }

    public int numTrees(int n) {
        // 动态规划 dps[i][j][k],k=i..j, 以k为根节点的二叉搜索树数量
        int[][][] dps = new int[n][n][n];
        for (int i = 0; i < n; i ++) {
            dps[i][i][i] = 1;
            if (i+1 < n) {
                dps[i][i+1][i] = 1;
                dps[i][i+1][i+1] = 1;
            }
        }
        int i = 0;
        for (int j = i+2; j < n; j ++) {
            for (int k = i; k <= j; k ++) {
                // cal dps[i][j][k]
                int left = 0, right = 0;
                for (int m = i; m < k; m ++) {
                    left += dps[i][k-1][m];
                }
                for (int m = k + 1; m <= j; m ++) {
                    right += dps[k+1][j][m];
                }
                dps[i][j][k] = Math.max(left, 1) * Math.max(right, 1);
                for (int l = 1; l+j < n; l ++) {
                    dps[i+l][j+l][k+l] = dps[i][j][k];
                }
            }
        }

        // cal dps[0][j][k]
        int ans = 0;
        for (int k = 0; k < n; k ++) {
            ans += dps[0][n-1][k];
        }
        return ans;

    }

    public int numTrees2(int n) {
        // 动态规划 dps[j], 0..j-1个的数量
        // G(n)= ∑ G(i−1)⋅G(n−i), i = 1..n
        int[] dps = new int[n+1];
        dps[0]=1;
        dps[1]=1;
        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= i; j ++) {
                dps[i] += dps[j-1]*dps[i-j];
            }
        }
        return dps[n];
    }
}
