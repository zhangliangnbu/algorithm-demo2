package com.liang.algo.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 通过次数151,204提交次数224,631
 */
public class FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        // use set O(n) O(n)
        Set<Integer> set = new HashSet<>();
        for (int val : nums) {
            if (set.contains(val)) {
                return val;
            } else {
                set.add(val);
            }
        }

        //没找到
        return -1;
    }

    public int findRepeatNumber2(int[] nums) {


        //没找到
        return -1;
    }
}
