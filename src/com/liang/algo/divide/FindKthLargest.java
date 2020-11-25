package com.liang.algo.divide;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        new FindKthLargest().findKthLargest(nums, 2);
    }

    public int findKthLargest(int[] nums, int k) {
        // 快速排序 递归划分
        int sortK = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, sortK);
    }

    private int quickSelect(int[] nums, int l, int r, int sortK) {
        int index = partition(nums, l, r);
        if (index == sortK) {
            return nums[index];
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
