package com.liang.algo.tree;

public class IsSubPath {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null || root == null) {
            return false;
        }
        return matchPath(head, root)
                || isSubPath(head, root.left)
                || isSubPath(head, root.right);


    }

    public boolean matchPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return (head.val == root.val)
                && (matchPath(head.next, root.left) || matchPath(head.next, root.right));
    }



      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

}
