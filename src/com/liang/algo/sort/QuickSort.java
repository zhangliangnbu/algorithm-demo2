package com.liang.algo.sort;

import com.liang.algo.Utils;

/**
 * 快速排序使用分治法（Divide and conquer）策略来把一个串行（list）分为两个子串行（sub-lists）。
 *
 * 快速排序的最坏运行情况是 O(n²)，比如说顺序数列的快排。
 * 但它的平摊期望时间是 O(nlogn)，且 O(nlogn) 记号中隐含的常数因子很小，
 * 比复杂度稳定等于 O(nlogn) 的归并排序要小很多。
 * 所以，对绝大多数顺序性较弱的随机数列而言，快速排序总是优于归并排序。
 *
 * 算法步骤
 * 从数列中挑出一个元素，称为 "基准"（pivot）;
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        QuickSort obj = new QuickSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = portion(nums, left, right);
        quickSort(nums, left, pivot);
        quickSort(nums, pivot + 1, right);
    }

    // 分配并返回基准点 pivot
    private int portion(int[] nums, int left, int right) {
        int pivot = left;
        // 待交换的元素
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index);
                index ++;
            }
        }
        // 将基准值与最后一个小于基准值的元素进行交换
        swap(nums, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
