package com.liang.algo.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeSerialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        rec1(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void rec1(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        rec1(root.left, sb);
        rec1(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        // data -> list
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        return buildTree(list);
    }

    private TreeNode buildTree(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        String s = list.remove(0);
        if (s.equals("#")) {
            return null;
        }
        int i = Integer.parseInt(s);
        TreeNode node = new TreeNode(i);
        TreeNode left = buildTree(list);
        TreeNode right = buildTree(list);
        node.left = left;
        node.right = right;
        return node;
    }
}
