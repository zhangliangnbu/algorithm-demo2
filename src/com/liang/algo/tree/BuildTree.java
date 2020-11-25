package com.liang.algo.tree;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

// 构造二叉树
public class BuildTree {
    public static void main(String[] args) {

    }

    // 中序和后序
    public TreeNode buildTreeByInPost(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        // 递归 自顶向下：先确定根节点，然后递归确定左、右子树
        // 在postorder中获取根节点的值，在inorder中确定根节点的位置和左、右子树的子区间
        return buildByInPost(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildByInPost(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int rootIndexOfInorder = indexOf(inorder, inStart, inEnd, rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 左子树节点个数
        int leftCount = rootIndexOfInorder - inStart;
        root.left = buildByInPost(inorder, inStart, rootIndexOfInorder - 1, postorder, postStart, postStart + leftCount - 1);
        root.right = buildByInPost(inorder, rootIndexOfInorder + 1, inEnd, postorder, postStart + leftCount, postEnd - 1);
        return root;
    }

    private int indexOf(int[] order, int inStart, int inEnd, int val) {
        for (int i = inStart; i <= inEnd; i ++) {
            if (val == order[i]) {
                return i;
            }
        }
        return -1;
    }

    // 中序和前序
    public TreeNode buildTreeByPreIn(int[] inorder, int[] preorder) {
        if (inorder == null || preorder == null || inorder.length == 0 || preorder.length == 0) {
            return null;
        }
        // 递归 自顶向下：先确定根节点，然后递归确定左、右子树
        // 在preorder中获取根节点的值，在inorder中确定根节点的位置和左、右子树的子区间
        return buildByPreIn(inorder, 0, inorder.length - 1,
                preorder, 0, preorder.length - 1);
    }

    private TreeNode buildByPreIn(int[] inorder, int inStart, int inEnd,
                                  int[] preorder, int preStart, int preEnd) {
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int rootIndexOfInorder = indexOf(inorder, inStart, inEnd, rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 左子树节点个数
        int leftCount = rootIndexOfInorder - inStart;
        root.left = buildByPreIn(inorder, inStart, rootIndexOfInorder - 1,
                preorder, preStart + 1, preStart + leftCount);
        root.right = buildByPreIn(inorder, rootIndexOfInorder + 1, inEnd,
                preorder, preStart + 1 + leftCount, preEnd);
        return root;
    }

    // 中序和前序 迭代
    public TreeNode buildTreeByPreIn2(int[] inorder, int[] preorder) {
        return null;
    }






}
