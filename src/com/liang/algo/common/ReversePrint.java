package com.liang.algo.common;

import com.liang.algo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 */
public class ReversePrint {
    public int[] reversePrint(ListNode head) {
        // 递归
        List<Integer> list = new ArrayList<>();
        dc(head, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dc(ListNode head, List<Integer> list) {
        if (head == null) {
            return;
        }
        dc(head.next, list);
        list.add(head.val);
    }
}
