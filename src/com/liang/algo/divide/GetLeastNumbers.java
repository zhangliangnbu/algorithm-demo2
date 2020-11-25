package com.liang.algo.divide;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 */
public class GetLeastNumbers {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        int index = quickSelect(arr, 0, arr.length - 1, k - 1);
        return Arrays.copyOf(arr, index + 1);
    }

    private int quickSelect(int[] nums, int l, int r, int sortK) {
        int index = partition(nums, l, r);
        if (index == sortK) {
            return index;
        }
        return index > sortK ? quickSelect(nums, l, index - 1, sortK)
                : quickSelect(nums, index + 1, r, sortK);
    }

    private int partition(int[] nums, int l, int r) {
        // 以nums[r]为标准进行 划分，小于等于此的放在左边，大于此的在右边，返回左边最后一个index
        int val = nums[r], index = l - 1;
        for (int i = l; i <= r; i++) {
            if (nums[i] <= val) {
                index ++;
                swap(nums, index, i);
            }
        }
        return index;

    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
