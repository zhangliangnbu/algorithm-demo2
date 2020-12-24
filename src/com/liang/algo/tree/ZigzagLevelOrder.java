package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * 通过次数82,145提交次数148,801
 */
public class ZigzagLevelOrder {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
        if (level % 2 == 1) {
            ts.get(level - 1).add(root.val);
        } else {
            ts.get(level - 1).add(0, root.val);
        }

        recursion(root.left, level + 1, ts);
        recursion(root.right, level + 1, ts);
    }

}
