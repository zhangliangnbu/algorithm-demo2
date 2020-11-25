package com.liang.algo.common;

import com.liang.algo.ListNode;

/**
 * 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * 通过次数93,256提交次数93,256
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }

        int len = 0;
        ListNode p = head;
        while (p != null) {
            len ++;
            p = p.next;
        }

        int m = k % len;
        if (m == 0) {
            return head;
        }
        ListNode first = head, mid = head, last = head;
        while (mid.next != null) {
            m --;
            mid = mid.next;
            if (m <= 0) {
                first = first.next;
            }
            if (m < 0) {
                last = last.next;
            }
        }
        mid.next = head;
        last.next = null;
        return first;
    }
}
