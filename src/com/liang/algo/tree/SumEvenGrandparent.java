package com.liang.algo.tree;

public class SumEvenGrandparent {
    public int sumEvenGrandparent(TreeNode root) {
        return rec(root, null, null);
    }

    public int rec(TreeNode root, TreeNode pRoot, TreeNode gRoot) {
        if (root == null) {
            return 0;
        }

        int current = 0;
        if (gRoot != null && gRoot.val % 2 == 0) {
            current = root.val;
        }

        int left = rec(root.left, root, pRoot);
        int right = rec(root.right, root, pRoot);
        return current + left + right;
    }
}
