package com.liang.algo.common;

import com.liang.algo.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        // 迭代
        ListNode node = head;
        ListNode pp = null, p = null;
        while (node != null) {
            if (p == null) {
                pp = p;
                p = node;
                node = node.next;
                continue;
            }

            if (pp == null) {
                p.next = node.next;
                node.next = p;
                head = node;
                pp = p;
                p = p.next;
                node = p == null ? null : p.next;
                continue;
            }

            p.next = node.next;
            node.next = p;
            pp.next = node;
            pp = p;
            p = p.next;
            node = p == null ? null : p.next;
        }

        return head;
    }
}
