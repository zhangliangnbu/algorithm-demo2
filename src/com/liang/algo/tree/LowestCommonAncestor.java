package com.liang.algo.tree;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归：自下而上 后序遍历 还是需要先穷举各种情况 再根据情况写递归
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

}
