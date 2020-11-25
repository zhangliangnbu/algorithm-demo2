package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaxDepth {
    public static void main(String[] args) {

    }

    public List<Integer> traversalByRecursion(TreeNode root) {
        // 递归
        List<Integer> ts = new ArrayList<>();
        recursion(root, ts);
        return ts;
    }

    private void recursion(TreeNode root, List<Integer> ts) {

    }

    private int downToUp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = downToUp(root.left);
        int rightDepth = downToUp(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    private int ans;
    // level: root 父节点层级
    private void upToDown(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans = Math.max(ans, level + 1);
        }
        upToDown(root.left, level + 1);
        upToDown(root.right, level + 1);
    }

    public List<Integer> traversalByIteration(TreeNode root) {
        // 迭代 使用栈
        List<Integer> ts = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ts.add(root.val);
            root = root.right;
        }
        return ts;
    }
}
