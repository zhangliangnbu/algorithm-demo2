package com.liang.algo.tree;

public class MaxPathSum {
    private int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        rec(root);
        return sum;
    }

    // 以root为起始点的最大路径和
    public int rec(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = rec(root.left);
        int right = rec(root.right);
        int cur = root.val;
        if (left < 0 && right < 0) {
            sum = Math.max(cur, sum);
        } else if (left > 0 && right > 0) {
            cur = Math.max(left, right) + cur;
            sum = Math.max(sum, left + right + root.val);
        } else {
            cur = Math.max(left, right) + cur;
            sum = Math.max(sum, cur);
        }

        return cur;
    }
}
