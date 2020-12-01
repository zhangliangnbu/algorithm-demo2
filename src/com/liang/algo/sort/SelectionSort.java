package com.liang.algo.sort;

import com.liang.algo.Utils;

/**
 * 找出最小值，放在无序区最前面
 *
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        SelectionSort obj = new SelectionSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            // i 表示无序区第一个数
            // 遍历无序区，找到最小值
            int minIndex = i;
            for (int j = i; j < nums.length; j ++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            // 将最小数与无序区第一个数交换
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }
}
