package com.liang.algo.window;

import com.liang.algo.Utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        Utils.print(maxSlidingWindow(nums, k));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }

        // 单调队列 顶部放置窗口的最大值
        Deque<Integer> deque = new LinkedList<>();
        int minLen = Math.min(k, nums.length);
        for (int i = minLen - 1; i >= 0; i --) {
            if (deque.isEmpty()) {
                deque.offerLast(nums[i]);
            } else if (nums[i] >= deque.peekFirst()){
                deque.offerFirst(nums[i]);
            }
        }

        int[] maxs;
        if (nums.length <= k) {
            maxs = new int[1];
            maxs[0] = deque.peekFirst();
            return maxs;
        } else {
            maxs = new int[nums.length - k + 1];
            maxs[0] = deque.peekFirst();
            for (int i = k; i < nums.length; i ++) {
                // 缩短
                if (!deque.isEmpty() && nums[i-k] == deque.peekFirst()) {
                    deque.pollFirst();
                }
                // 扩大
                if (!deque.isEmpty() && deque.peekFirst() < nums[i]) {
                    deque.clear();
                } else {
                    while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                        deque.pollLast();
                    }
                }
                deque.offerLast(nums[i]);
                maxs[i - k + 1] = deque.peekFirst();
            }
        }

        return maxs;
    }
}
