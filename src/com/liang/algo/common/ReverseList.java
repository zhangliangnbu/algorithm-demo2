package com.liang.algo.common;

import com.liang.algo.ListNode;

import java.util.Stack;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseList {

    private ListNode root;
    public ListNode reverseList(ListNode head) {
        rc(head);
        return root;
    }

    // 返回的反转后的最后一个节点
    public ListNode rc(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            root = head;
            return head;
        }
        ListNode parent = rc(head.next);
        parent.next = head;
        head.next = null;
        return head;
    }

    public ListNode reverseList2(ListNode head) {
        // 迭代 栈
        if (head == null) {
            return null;
        }

        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode root = stack.pop();
        ListNode p = root;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = null;
            p.next = node;
            p = p.next;
        }
        return root;
    }

}
