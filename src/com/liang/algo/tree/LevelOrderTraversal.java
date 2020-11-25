package com.liang.algo.tree;

import java.util.*;

// DFS-中序遍历 left root right
public class LevelOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> traversalByRecursion(TreeNode root) {
        // 递归
        List<List<Integer>> ts = new ArrayList<>();
        recursion(root, 1, ts);
        return ts;
    }

    private void recursion(TreeNode root, int level, List<List<Integer>> ts) {
        if (root == null) {
            return;
        }
        if (level > ts.size()) {
            ts.add(new ArrayList<>());
        }
        ts.get(level - 1).add(root.val);
        recursion(root.left, level + 1, ts);
        recursion(root.right, level + 1, ts);
    }

    public List<List<Integer>> traversalByIteration(TreeNode root) {
        // 迭代 使用队列
        List<List<Integer>> ts = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        List<Integer> ss = new ArrayList<>();
        // 记录当前ss长度上限
        int ssLen = 1;
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }

            // 当前需要的长度
            ss.add(root.val);
            if (ssLen == ss.size()) {
                ts.add(ss);
                ss = new ArrayList<>();
                ssLen = queue.size();
            }
        }
        return ts;
    }
}
