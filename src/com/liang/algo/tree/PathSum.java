package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum {

    private final List<List<Integer>> tracks = new ArrayList<>();
    private int ps = 0;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // 回溯
        if (root == null) {
            return tracks;
        }

        LinkedList<Integer> track = new LinkedList<>();
        // 根节点是必须选择的
        track.add(root.val);
        ps += root.val;
        backtrack(root, sum, track);
        return tracks;
    }

    public void backtrack(TreeNode root, int sum, LinkedList<Integer> track) {
        if (root.left == null && root.right == null) {
            if (ps == sum) {
                tracks.add(new ArrayList<>(track));
            }
            return;
        }

        TreeNode left = root.left;
        if (left != null) {
            track.add(left.val);
            ps += left.val;
            backtrack(left, sum, track);
            ps -= track.removeLast();
        }

        TreeNode right = root.right;
        if (right != null) {
            track.add(right.val);
            ps += right.val;
            backtrack(right, sum, track);
            ps -= track.removeLast();
        }
    }

}
