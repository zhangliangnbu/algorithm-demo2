package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// DFS-中序遍历 left root right
public class InorderTraversal {
    public static void main(String[] args) {

    }

    public List<Integer> traversalByRecursion(TreeNode root) {
        // 递归
        List<Integer> ts = new ArrayList<>();
        recursion(root, ts);
        return ts;
    }

    private void recursion(TreeNode root, List<Integer> ts) {
        if (root == null) {
            return;
        }
        recursion(root.left, ts);
        ts.add(root.val);
        recursion(root.right, ts);
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
