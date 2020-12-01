package com.liang.algo.sort;

import com.liang.algo.Utils;

import java.util.Arrays;

/**
 * 算法过程
 * 根据待排序集合中最大元素和最小元素的差值范围和映射规则，确定申请的桶个数；
 * 遍历待排序集合，将每一个元素移动到对应的桶中；
 * 对每一个桶中元素进行排序，并移动到已排序集合中。
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        BucketSort obj = new BucketSort();
        obj.sort(nums, 3);
        Utils.print(nums);
    }

    public void sort(int[] nums, int bucketSize) {
        // 构建桶数组
        int[][] buckets = new int[bucketSize][0];
        int min = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        int bucketSpan = (max - min) / bucketSize + 1;
        for (int val : nums) {
            int index = (val - min) / bucketSpan;
            buckets[index] = arrAppend(buckets[index], val);
        }
        // 排序
        int index = 0;
        for (int[] bucket : buckets) {
            // 桶内排序
            if (bucket.length == 0) {
                continue;
            }
            Arrays.sort(bucket);
            for (int b : bucket) {
                nums[index] = b;
                index ++;
            }
        }
    }

    private int[] arrAppend(int[] arr, int val) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = val;
        return arr;
    }
}
