package com.liang.algo.tree;

public class ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return rec(nums, 0, nums.length - 1);
    }

    private TreeNode rec(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = getMaxIndex(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = rec(nums, start, maxIndex - 1);
        root.right = rec(nums, maxIndex + 1, end);
        return root;
    }

    private int getMaxIndex(int[] nums, int start, int end) {
        int max = start;
        for (int i = start; i <= end; i ++) {
            if (nums[max] < nums[i]) {
                max = i;
            }
        }
        return max;
    }



}
