package com.liang.algo.common;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * 通过次数164,265提交次数289,604
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        // 双指针
        if (numbers == null) {
            return null;
        }
        if (numbers.length == 0) {
            return new int[0];
        }

        int first = 0, last = numbers.length - 1;
        int[] index = new int[2];
        while (first < last) {
            int sum = numbers[first] + numbers[last];
            if (sum == target) {
                index[0] = first + 1;
                index[1] = last + 1;
                break;
            }
            if (sum > target) {
                last --;
            } else {
                first ++;
            }
        }

        return index;
    }
}
