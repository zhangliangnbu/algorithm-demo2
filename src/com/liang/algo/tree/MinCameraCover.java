package com.liang.algo.tree;

/**
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 *
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 *
 * 计算监控树的所有节点所需的最小摄像头数量。
 */
public class MinCameraCover {

    private int count = 0;
    public int minCameraCover(TreeNode root) {
        // 思路：down 2 up
        rec(root);
        return count;
    }

    public void rec(TreeNode root) {
        // 0 - 被照 1 - 仅照自己 2 - 照自己并照他人
        if (root == null) {
            return;
        }
        rec(root.left);
        rec(root.right);

        int left = root.left == null ? 0 : root.left.val;
        int right = root.right == null ? 0 : root.right.val;
        if (left == 0 && right == 0) {
            root.val = 1;
            count ++;
        } else if (left == 1 && right == 1) {
            root.val = 2;
            count --;
        } else if (left == 1 || right == 1) {
            root.val = 2;
        }
    }

}
