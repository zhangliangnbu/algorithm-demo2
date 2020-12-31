package com.liang.algo.window;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 862. 和至少为 K 的最短子数组
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 *
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 * 示例 1：
 *
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 */
public class ShortestSubarray {
    public static void main(String[] args) {
        ShortestSubarray obj = new ShortestSubarray();
        int[] A = {-34,37,51,3,-12,-50,51,100,-47,99,34,14,-13,89,31,-14,-44,23,-38,6};
        int K = 151;
        System.out.println(obj.shortestSubarray2(A, K));

//        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> A[o]));
//        for (int i = 0; i < A.length; i ++) {
//            queue.offer(i);
//        }
//        while (!queue.isEmpty()) {
//            System.out.println(queue.poll());
//        }
    }

    public int shortestSubarray(int[] A, int K) {
        // 滑动窗口
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0, right = 0, window = 0, minSub = -1;
        // 辅助集合 前缀和
        int[] sums = new int[A.length];
        for (int i = 1; i < A.length; i ++) {
            sums[i] = sums[i-1] + A[i];
        }
        while (right < A.length) {
            // 扩大窗口
            int r = A[right];
            right ++;
            window += r;
            if (window <= 0) {
                left = right;
                window = 0;
                continue;
            }

            // 缩小窗口
            while (left < right && window >= K) {
                // 更新值
                int min = right - left;
                if (minSub == -1) {
                    minSub = min;
                } else {
                    minSub = Math.min(minSub, min);
                }
                // 缩小窗口
                if (left >= right - 1) {
                    break;
                }
                left = findMinIndex(sums, left, right-1);
                window = sums[right-1] - sums[left];
                left ++;
            }
        }

        return minSub;
    }

    public int findMinIndex(int[] sums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = l + (r - l) / 2;
        int lm = findMinIndex(sums, l, mid);
        int rm = findMinIndex(sums, mid + 1, r);
        if (sums[lm] < sums[rm]) {
            return lm;
        } else {
            return rm;
        }
    }

    public int shortestSubarray2(int[] A, int K) {
        // 滑动窗口
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0, right = 0, window = 0, minSub = -1;
        // 辅助集合 前缀和
        int[] sums = new int[A.length];
        for (int i = 1; i < A.length; i ++) {
            sums[i] = sums[i-1] + A[i];
        }
        // 辅助队列 最小堆
        Deque<Integer> queue = new LinkedList<>();
        while (right < A.length) {
            // 扩大窗口
            int r = A[right];
            while (!queue.isEmpty() && sums[right] <= sums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.offerLast(right);
            right ++;
            window += r;
            if (window <= 0) {
                left = right;
                window = 0;
                queue.clear();
                continue;
            }

            // 缩小窗口
            while (left < right && window >= K) {
                // 更新值
                int min = right - left;
                if (minSub == -1) {
                    minSub = min;
                } else {
                    minSub = Math.min(minSub, min);
                }
                // 缩小窗口
                if (queue.isEmpty()) {
                    break;
                }
                left = queue.poll();
                window = sums[right-1] - sums[left];
                left ++;
            }
        }

        return minSub;
    }


}
