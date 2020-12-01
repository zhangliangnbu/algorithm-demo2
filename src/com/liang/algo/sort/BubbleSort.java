package com.liang.algo.sort;

import com.liang.algo.Utils;

/**
 *
 * 大数沉底
 *
 * 算法步骤
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        BubbleSort obj = new BubbleSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i --) {
            // i 表示无序区的最后一个数的索引
            // 对无序区遍历，将大数沉底
            for (int j = 0; j < i; j ++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
