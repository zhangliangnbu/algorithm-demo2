package com.liang.algo.common;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 通过次数142,924提交次数352,990
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            int[] temp = new int[2];
            temp[0] = -1;
            temp[1] = -1;
            return temp;
        }
        return dc(nums, 0, nums.length - 1, target);
    }

    private int[] dc(int[] nums, int start, int end, int target) {
        int[] temp = {-1, -1};

        if (start > end || target < nums[start] || target > nums[end]) {
            return temp;
        }

        if (start == end) {
            if (nums[start] == target) {
                temp[0] = start;
                temp[1] = start;
            }
            return temp;
        }

        if (nums[start] == target && nums[end] == target) {
            temp[0] = start;
            temp[1] = end;
            return temp;
        }

        int mid = (start + end) / 2;
        int[] left = dc(nums, start, mid, target);
        int[] right = dc(nums, mid + 1, end, target);

        temp[0] = left[0] != -1 ? left[0] : right[0];
        temp[1] = right[1] != -1 ? right[1] : left[1];
        return temp;
    }
}
