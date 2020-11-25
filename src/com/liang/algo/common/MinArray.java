package com.liang.algo.common;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * 通过次数112,217提交次数227,204
 */
public class MinArray {

    public int minArray(int[] numbers) {
        // 二分查找
        int index = dc(numbers, 0, numbers.length - 1);
        return numbers[index];
    }

    // return index
    private int dc(int[] numbers, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;

        if (numbers[mid] > numbers[end]) {
            return dc(numbers, mid + 1, end);
        } else if (numbers[mid] < numbers[end]){
            return dc(numbers, start, mid);
        } else {
            int left = dc(numbers, start, mid);
            int right = dc(numbers, mid + 1, end);
            return numbers[left] > numbers[right] ? right : left;
        }
    }

}
