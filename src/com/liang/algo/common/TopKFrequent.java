package com.liang.algo.common;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // 优先队列O(nlg(k))

        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }

        // int[0] 数，int[1] 数的频次
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : set) {
            if (q.size() < k) {
                int[] a = {entry.getKey(), entry.getValue()};
                q.offer(a);
            } else if (entry.getValue() > q.peek()[1]) {
                q.poll();
                int[] a = {entry.getKey(), entry.getValue()};
                q.offer(a);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i ++) {
            ans[i] = q.poll()[0];
        }
        return ans;
    }
}
