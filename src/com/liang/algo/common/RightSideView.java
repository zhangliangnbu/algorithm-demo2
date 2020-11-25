package com.liang.algo.common;

import com.liang.algo.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        // 层次遍历
        if (root == null) {
            return new LinkedList<>();
        }
        // queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // ans
        List<Integer> ans = new ArrayList<>();
        // 当前节点总数
        int nextLevelLen = 1;
        // 当前层已经取出的节点数
        int curLevelOutLen = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevelOutLen ++;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 当前层最后一个节点
            if (curLevelOutLen == nextLevelLen) {
                nextLevelLen = queue.size();
                curLevelOutLen = 0;
                ans.add(node.val);
            }
        }
        return ans;
    }
}
