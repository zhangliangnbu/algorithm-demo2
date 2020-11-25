package com.liang.algo.common;

import com.liang.algo.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        // hashset
        if (head == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if(!set.add(p)) {
                return p;
            }
            p = p.next;
        }

        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        // 快慢指针 a = (n-1)*(b+c) + c
        // b 为入环点到相遇点距离，c为相遇点到入环点距离，a为头结点到入环点距离
        // 快慢指针相遇后，让另外指针从头开始走，同慢指针一起每次一步，新指针和慢指针相遇点即为入环点
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head.next.next, p = head;
        while (true) {
            if (slow == null || fast == null || fast.next == null) {
                return null;
            }
            if (fast == slow) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }

        return p;
    }
}
