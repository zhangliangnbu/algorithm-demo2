package com.liang.algo.tree;

public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return rec(nums, 0, nums.length - 1);
    }

    private TreeNode rec(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int midIndex = (start + end + 1) / 2;
        TreeNode root = new TreeNode(nums[midIndex]);
        root.left = rec(nums, start, midIndex - 1);
        root.right = rec(nums, midIndex + 1, end);
        return root;
    }
}
