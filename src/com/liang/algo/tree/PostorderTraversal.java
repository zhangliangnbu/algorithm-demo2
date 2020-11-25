package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// DFS-后序遍历 left right root
public class PostorderTraversal {
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
        recursion(root.right, ts);
        ts.add(root.val);
    }

    public List<Integer> traversalByIteration(TreeNode root) {
        // 迭代 使用栈
        List<Integer> ts = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }
        while (!stack.empty()) {
            root = stack.pop();
            ts.add(0, root.val);
            if (root.left != null) {
                stack.add(root.left);
            }
            if (root.right != null) {
                stack.add(root.right);
            }
        }
        return ts;
    }
}
