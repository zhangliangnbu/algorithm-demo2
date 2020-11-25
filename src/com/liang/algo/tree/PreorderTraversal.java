package com.liang.algo.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 二叉树的先序遍历
public class PreorderTraversal {
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
        ts.add(root.val);
        recursion(root.left, ts);
        recursion(root.right, ts);
    }

    public List<Integer> traversalByIteration(TreeNode root) {
        // 迭代 使用栈
        List<Integer> ts = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.empty()) {
            TreeNode r = stack.pop();
            ts.add(r.val);
            if (r.right != null) {
                stack.push(r.right);
            }
            if (r.left != null) {
                stack.push(r.left);
            }
        }
        return ts;
    }


}
