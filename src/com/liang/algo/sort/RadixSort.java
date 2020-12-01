package com.liang.algo.sort;

import com.liang.algo.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无论是最高位优先法还是最低位优先法进行遍历需要保证数字各位、十位、百位等对齐，这里我使用最低位优先法从个位开始向上。
 * 数字类型的基数排序需要十个桶(0-9)，你可以使用二维数组，第一维度长度为10表示十个数字，第二个维度为数组长度，用来存储数字(因为最坏情况可能当前位数字一样)。但这样无疑太浪费内存空间了，你可以使用List或者Queue替代，这里就用List了。
 * 具体实现要先找到最大值确定最高多少位，用来进行遍历时候确认。
 * 收集的时候借助一个自增参数遍历收集。
 * 每次收集完毕十个桶(bucket)需要清空待下次收集。
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] nums = {9346,31,8,4,251,51,14,3,665,76,71};
        RadixSort obj = new RadixSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] nums) {
        // 以正整数为例

        // 最大值
        int max = nums[0];
        for (int val : nums) {
            if (max < val) {
                max = val;
            }
        }

        // 遍历排序
        int dev = 1;
        while (max > 0) {
            // 构造桶数组。因为桶要重复利用，所以每次都需要置空
            int[][] buckets = new int[10][0];

            // dev位数的入桶
            for (int val : nums) {
                int index = (val / dev) % 10;
                buckets[index] = arrAppend(buckets[index], val);
            }

            // 出桶排序
            int pos = 0;
            for (int[] bucket : buckets) {
                for (int b : bucket) {
                    nums[pos] = b;
                    pos ++;
                }
            }

            dev *= 10;
            max /= 10;
        }
    }

    private int[] arrAppend(int[] arr, int val) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = val;
        return arr;
    }
}
