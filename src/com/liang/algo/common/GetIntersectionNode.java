package com.liang.algo.common;

import com.liang.algo.ListNode;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 对齐、遍历
        // 解释：尾部对齐；从开始对齐的节点同时遍历，相等则为相交
        int lenA = 0, lenB = 0;
        ListNode pa = headA, pb = headB;
        while (pa != null) {
            lenA ++;
            pa = pa.next;
        }
        while (pb != null) {
            lenB ++;
            pb = pb.next;
        }
        int as = lenA >= lenB ? lenA - lenB : 0;
        int bs = lenA >= lenB ? 0 : lenB - lenA;
        pa = headA;
        pb = headB;
        while (pa != null) {
            if (as == 0) {
                break;
            }
            as --;
            pa = pa.next;
        }
        while (pb != null) {
            if (bs == 0) {
                break;
            }
            bs --;
            pb = pb.next;
        }

        while (pa != null && pb != null) {
            if (pa == pb) {
                return pa;
            }
            pa = pa.next;
            pb = pb.next;
        }
        return null;
    }


}
