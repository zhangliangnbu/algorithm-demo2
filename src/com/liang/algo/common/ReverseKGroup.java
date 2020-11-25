package com.liang.algo.common;

import com.liang.algo.ListNode;

public class ReverseKGroup {
    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     *
     *
     * 示例：
     *
     * 给你这个链表：1->2->3->4->5
     *
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     *
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     *
     *
     * 说明：
     *
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // cal len
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len ++;
            p = p.next;
        }
        return dc(head, k, 0, len);
    }

    // 返回头结点
    public ListNode dc(ListNode head, int k, int order, int len) {
        if (head == null || head.next == null || order >= (len / k) * k) {
            return head;
        }
        ListNode node = dc(head.next, k, order + 1, len);
        // 周期末位不需要处理
        if ((order + 1) % k == 0) {
            head.next = node;
            return head;
        }
        // 周期内其他位 添加到末尾
        ListNode tail = node;
        int temp = order + 1;
        while ((temp + 1) % k != 0) {
            tail = tail.next;
            temp ++;
        }
        head.next = tail.next;
        tail.next = head;
        return node;
    }
}
