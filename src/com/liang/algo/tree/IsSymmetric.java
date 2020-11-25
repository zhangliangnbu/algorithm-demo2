package com.liang.algo.tree;

import java.util.*;

public class IsSymmetric {
    public static void main(String[] args) {

    }

    public boolean traversalByRecursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recursion2(root.left, root.right);
    }

    public boolean recursion2(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return recursion2(left.left, right.right) && recursion2(left.right, right.left);
    }

    public boolean traversalByIteration(TreeNode root) {
        // 迭代 使用队列
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            if (n1 == null && n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null) {
                return false;
            }
            if (n1.val != n2.val) {
                return false;
            }
            queue.add(n1.left);
            queue.add(n2.right);
            queue.add(n1.right);
            queue.add(n2.left);
        }

        return true;
    }
}
