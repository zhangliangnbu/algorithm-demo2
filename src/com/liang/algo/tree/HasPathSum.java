package com.liang.algo.tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        // 递归
        if (root == null) {
            return false;
        }
        return recursion(root, sum);
    }

    public boolean recursion(TreeNode root, int need) {
        // 递归
        TreeNode left = root.left;
        TreeNode right = root.right;
        need = need - root.val;
        if (left == null && right == null && need == 0) {
            return true;
        }
        if (left == null && right == null) {
            return false;
        }
        if (left == null) {
            return recursion(right, need);
        }
        if (right == null) {
            return recursion(left, need);
        }
        return recursion(left, need) || recursion(right, need);
    }

    public boolean traversalByIteration(TreeNode root, int sum) {
        // 迭代 使用队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return false;
        }
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            if (left.left == null && left.right == null && sum - left.val == 0) {
                return true;
            }



            TreeNode right = queue.poll();
            if (right.left == null && right.right == null && sum - left.val == 0) {
                return true;
            }


        }
        return false;
    }

}
