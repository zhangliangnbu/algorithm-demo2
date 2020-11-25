package com.liang.algo.tree;

/**
 * 1302. 层数最深叶子节点的和
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 *
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *
 *
 * 提示：
 *
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 */
public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        int maxDepth = getDepth(root, 1);
        return getLeafSum(root, 1, maxDepth);
    }

    // depth 为root的深度
    public int getDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth - 1;
        }

        int left = getDepth(root.left, depth + 1);
        int right = getDepth(root.right, depth + 1);

        return Math.max(left, right);
    }

    // 获取目标深度的叶子结点和
    public int getLeafSum(TreeNode root, int currentDepth, int targetDepth) {
        if (root == null) {
            return 0;
        }
        if (currentDepth == targetDepth) {
            return root.val;
        }
        int left = getLeafSum(root.left, currentDepth + 1, targetDepth);
        int right = getLeafSum(root.right, currentDepth + 1, targetDepth);
        return left + right;
    }

    private int total = 0;
    private int maxDpt = -1;
    public void calSum(TreeNode root, int parentDpt) {
        if (root == null) {
            return;
        }
        int currentDpt = parentDpt + 1;
        if (currentDpt > maxDpt) {
            maxDpt = currentDpt;
            total = root.val;
        } else if (currentDpt == maxDpt) {
            total += root.val;
        }

        calSum(root.left, currentDpt);
        calSum(root.right, currentDpt);
    }

    public int deepestLeavesSum1(TreeNode root) {
        calSum(root, 0);
        return total;
    }

}
