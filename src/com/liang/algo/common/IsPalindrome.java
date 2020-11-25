package com.liang.algo.common;

import com.liang.algo.ListNode;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        // O(n)&O(1)
        // 递归反转后半部分链表、迭代比较、恢复链表

        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        // 计算长度
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n ++;
            p = p.next;
        }

        // 后半部分起始节点
        int lastStart = n / 2 + n % 2;
        p = head;
        int count = 0;
        while (count < lastStart) {
            p = p.next;
            count ++;
        }

        // 反转后半部分链表
        ListNode reverseLastHead = reverse(p);

        // 比较
        count = n / 2;
        ListNode p1 = head;
        ListNode p2 = reverseLastHead;
        while (count != 0) {
            count --;
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 恢复列表
        // ...

        return true;
    }

    private ListNode reverse(ListNode root) {
        if (root.next == null) {
            return root;
        }
        ListNode newRoot = reverse(root.next);

        ListNode p = newRoot;
        while (p.next != null) {
            p = p.next;
        }
        p.next = root;
        root.next = null;

        return newRoot;
    }


    /**
     * 125. 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     */
    public boolean isPalindromeStr(String s) {
        if (s == null) {
            return false;
        }
        String ss = s.trim();
        if (ss.isEmpty()) {
            return true;
        }

        int head = 0, tail = ss.length() - 1;
        while (head <= tail) {
            char hc = Character.toLowerCase(ss.charAt(head));
            if (!isValidChar(hc)) {
                head ++;
                continue;
            }
            char tc = Character.toLowerCase(ss.charAt(tail));
            if (!isValidChar(tc)) {
                tail --;
                continue;
            }
            if (Character.toLowerCase(ss.charAt(head)) != Character.toLowerCase(ss.charAt(tail))) {
                return false;
            }
            head ++;
            tail --;
        }
        return true;
    }

    private boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
