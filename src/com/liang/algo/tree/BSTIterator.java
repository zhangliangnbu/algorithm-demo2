package com.liang.algo.tree;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        pushLeft(root);
    }
    private void pushLeft(TreeNode root) {
        if (root != null) {
            stack.push(root);
            pushLeft(root.left);
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode root = stack.pop();
        if (root.right != null) {
            pushLeft(root.right);
        }
        return root.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
