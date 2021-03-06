package com.liang.algo.common;

import com.liang.algo.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {
    private int count = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            count = 0;
            return null;
        }
        head.next = removeNthFromEnd(head.next, n);
        count ++;
        if (count == n) {
            return head.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 迭代
        if (head == null) {
            return null;
        }
        if (n == 0) {
            return head;
        }

        // cal remove index
        int size = 0;
        ListNode p = head;
        while (p != null) {
            size ++;
            p = p.next;
        }
        int removeIndex = size - n;
        if (removeIndex < 0) {
            return head;
        }

        // remove
        p = head;
        ListNode parent = null;
        int index = -1;
        while (p != null) {
            index ++;

            if (index == removeIndex) {
                if (parent == null) {
                    return p.next;
                } else {
                    parent.next = p.next;
                    break;
                }
            }
            parent = p;
            p = p.next;
        }

        return head;
    }
}
