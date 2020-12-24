package com.liang.algo.linkedlist;

import com.liang.algo.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dump = new ListNode(0);
        dump.next = head;
        ListNode pre = dump, p = dump.next;
        while (p != null && p.next != null) {
            boolean delete = false;
            while (p.next != null && p.val == p.next.val) {
                p = p.next;
                delete = true;
            }
            if (!delete) {
                pre = p;
            } else {
                pre.next = p.next;
            }
            p = p.next;
        }

        return dump.next;
    }

}
