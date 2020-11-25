package com.liang.algo.tree;

/**
 * 1028. 从先序遍历还原二叉树
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 */
public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
        // 前序遍历
        return recoverFromPreorderRec("", S);
    }

    public TreeNode recoverFromPreorderRec(String split, String s) {
        // current
        if (s == null || s.length() == 0) {
            return null;
        }

        String csp = split.concat("-");
        int cspIndex = s.indexOf(csp);
        if (cspIndex < 0) {
            int val = Integer.parseInt(s);
            return new TreeNode(val);
        }
        int val = Integer.parseInt(s.substring(0, cspIndex));
        TreeNode root = new TreeNode(val);

        // left and right
        int leftStart = findIndex(s, 1, csp);
        if (leftStart < 0) {
            return root;
        }
        int rightStart = findIndex(s, leftStart, csp);
        if (rightStart < 0) {
            root.left = recoverFromPreorderRec(csp, s.substring(leftStart));
        } else {
            root.left = recoverFromPreorderRec(csp, s.substring(leftStart, rightStart - csp.length()));
            root.right = recoverFromPreorderRec(csp, s.substring(rightStart));
        }
        return root;
    }

    private int findIndex(String s, int start, String target) {
        if (target == null || target.length() == 0) {
            return -1;
        }
        int tl = target.length();
        int count = 0;
        for (int i = start; i < s.length(); i ++) {
            // 判断
            if (count == tl && s.charAt(i) != '-') {
                return i;
            }
            if (s.charAt(i) == '-') {
                count ++;
            } else {
                count = 0;
            }
        }
        return -1;
    }

}
