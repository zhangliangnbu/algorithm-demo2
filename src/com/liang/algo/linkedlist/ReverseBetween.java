package com.liang.algo.linkedlist;

import com.liang.algo.ListNode;

import java.util.List;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 通过次数82,424提交次数159,596
 */
public class ReverseBetween {

    public static void main(String[] args) {
        int mode = 0x010101;
        int type = 0x00ff00;
        int val = 0x001100;
        int m = mode & type ^ mode | val;
        int mm = (~type) & mode | val;
        System.out.println(Integer.toHexString(m));
        System.out.println(Integer.toHexString(mm));
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n) {
            return head;
        }
        ListNode dump = new ListNode(0);
        dump.next = head;

        ListNode p = dump.next, pre = dump;
        int order = 1;
        while (p != null && p.next != null) {
           if (order < m) {
               pre = p;
               p = p.next;
               order ++;
               continue;
           }

           if (order > n - 1) {
               break;
           }

           ListNode temp = p.next;
           p.next = p.next.next;
           temp.next = pre.next;
           pre.next = temp;
           order ++;
        }

        return dump.next;
    }

}
