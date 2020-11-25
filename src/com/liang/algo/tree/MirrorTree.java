package com.liang.algo.tree;

public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode nr = new TreeNode(root.val);
        nr.left = mirrorTree(root.right);
        nr.right = mirrorTree(root.left);
        return nr;
    }
}
