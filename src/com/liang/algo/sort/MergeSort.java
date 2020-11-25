package com.liang.algo.sort;

import com.liang.algo.Utils;

import java.util.Arrays;

/**
 * 归并排序
 * 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 *
 * 作为一种典型的分而治之思想的算法应用，归并排序的实现由两种方法：
 *
 * 自上而下的递归（所有递归的方法都可以用迭代重写，所以就有了第 2 种方法）；
 * 自下而上的迭代；
 *
 *
 * 算法步骤
 * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
 *
 * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
 *
 * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
 *
 * 重复步骤 3 直到某一指针达到序列尾；
 *
 * 将另一序列剩下的所有元素直接复制到合并序列尾。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        MergeSort obj = new MergeSort();
//        obj.sort(nums);
        Utils.print(obj.sort(nums));
        Utils.print(nums);
    }

    public int[] sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int midIndex = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, midIndex);
        int[] right = Arrays.copyOfRange(nums, midIndex, nums.length);
        return merge(sort(left), sort(right));
    }


    public int[] merge(int[] left, int[] right) {
        int[] arr = new int[left.length + right.length];
        int l = 0, r = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                arr[l+r] = left[l];
                l ++;
            } else {
                arr[l+r] = right[r];
                r ++;
            }
        }
        if (l >= left.length) {
            System.arraycopy(right, r, arr, left.length + r, right.length - r);
        } else {
            System.arraycopy(left, l, arr, l + right.length, left.length - l);
        }
        return arr;
    }
}
