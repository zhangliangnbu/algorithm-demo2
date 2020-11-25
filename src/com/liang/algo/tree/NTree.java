package com.liang.algo.tree;

import sun.reflect.misc.MethodUtil;

import java.util.ArrayList;
import java.util.List;

public class NTree {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        rec(root, list);
        return list;
    }

    private void rec(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.children == null) {
            list.add(root.val);
            return;
        }

        for (Node node:root.children) {
            rec(node, list);
        }
        list.add(root.val);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderRec(root, list);
        return list;
    }

    public void preorderRec(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.children == null) {
            return;
        }
        for (Node node : root.children) {
            preorderRec(node, list);
        }
    }

    /**
     * 559. N叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     *
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     */
    public int maxDepth(Node root) {
        // 后续遍历 获取子节点的高度
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        int max = 0;
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }

    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        // 前序遍历 存储 遍历计算
        List<List<Integer>> lists = new ArrayList<>();
        averageOfLevelsRec(root, lists, 1);
        List<Double> dList = new ArrayList<>();
        for (List<Integer> list : lists) {
            if (list.size() == 0) {
                continue;
            }
            long d = 0;
            int size = list.size();
            for (long i : list) {
                d += i;
            }
            dList.add(1.0 * d / size);
        }
        return dList;
    }

    public void averageOfLevelsRec(TreeNode root, List<List<Integer>> lists, int level) {
        if (root == null) {
            return;
        }
        if (level > lists.size()) {
            lists.add(new ArrayList<>());
        }
        lists.get(level - 1).add(root.val);
        averageOfLevelsRec(root.left, lists, level + 1);
        averageOfLevelsRec(root.right, lists, level + 1);
    }






}
