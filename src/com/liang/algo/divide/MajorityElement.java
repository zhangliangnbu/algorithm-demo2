package com.liang.algo.divide;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        // 方法一：排序，迭代 空间为O(nlgn)，时间为O(1)
        // 方法二：用hashmap存储，迭代找最大值，空间和时间都为O(n)
        // 方法三：分治
        return dc(nums, 0, nums.length - 1);
        // 方法四：统计
    }

    private int statistics(int [] nums) {
        int flag = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (count == 0) {
                flag = nums[i];
                count ++;
                continue;
            }

            if (nums[i] == flag) {
                count ++;
            } else {
                count --;
            }
        }

        return flag;
    }

    private int dc(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[end];
        }

        int mid = (start + end) / 2;
        int left = dc(nums, start, mid);
        int right = dc(nums, mid + 1, end);

        int lc = count(nums, start, end, left);
        int rc = count(nums, start, end, right);
        return lc >= rc ? left : right;
    }

    private int count(int[] nums, int start, int end, int val) {
        int count = 0;
        for (int i = start; i <= end; i ++) {
            if (nums[i] == val) {
                count ++;
            }
        }
        return count;
    }

}
