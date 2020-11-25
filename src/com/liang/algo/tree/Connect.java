package com.liang.algo.tree;

public class Connect {



    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = root.next == null ? null : root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;

    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right != null ? root.right : findNext(root);
        }
        if (root.right != null) {
            root.right.next = root.next == null ? null : findNext(root);
        }
        // 需要先求右子树，因为左子树的next链表依赖右子树
        connect(root.right);
        connect(root.left);
        return root;
    }

    private Node findNext(Node root) {
        if (root == null) {
            return null;
        }
        Node rootNext = root.next;
        while (rootNext != null) {
            if (rootNext.left != null) {
                return rootNext.left;
            }
            if (rootNext.right != null) {
                return rootNext.right;
            }
            rootNext = rootNext.next;
        }
        return null;
    }



    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
