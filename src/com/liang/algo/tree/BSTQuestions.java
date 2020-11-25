package com.liang.algo.tree;

public class BSTQuestions {
    /**
     * 701. 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            // left
            root.left = insertIntoBST(root.left, val);
        } else {
            // right
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTRec(root, null, null);
    }

    public boolean isValidBSTRec(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        boolean current = (min == null || root.val > min.val) && (max == null || root.val < max.val);
        boolean left = isValidBSTRec(root.left, min, root);
        boolean right = isValidBSTRec(root.right, root, max);
        return current && left && right;
    }

    /**
     * 99. 恢复二叉搜索树
     * 二叉搜索树中的两个节点被错误地交换。
     *
     * 请在不改变其结构的情况下，恢复这棵树。
     */
    private TreeNode pre;
    public void recoverTree(TreeNode root) {
        // 方法一：中序遍历存入数组，排序，递归构建树
        // 方法二：递增和递减遍历，保留上一个节点信息，比较值，交换
        // 下面为方法二
        pre = null;
        recMoveLow(root);
        pre = null;
        recMoveHigh(root);
    }

    public void recMoveLow(TreeNode root) {
        // 右中左遍历，保留上一个节点信息，比较值，交换 将错误的小值移到正确位置
        if (root == null) {
            return;
        }
        recMoveLow(root.right);
        if (pre != null && root.val > pre.val) {
            int temp = root.val;
            root.val = pre.val;
            pre.val = temp;
        }
        pre = root;
        recMoveLow(root.left);
    }

    public void recMoveHigh(TreeNode root) {
        // 左中右遍历，保留上一个节点信息，比较值，交换 将错误的大值移到正确位置
        if (root == null) {
            return;
        }
        recMoveHigh(root.left);
        if (pre != null && root.val < pre.val) {
            int temp = root.val;
            root.val = pre.val;
            pre.val = temp;
        }
        pre = root;
        recMoveHigh(root.right);
    }
}
