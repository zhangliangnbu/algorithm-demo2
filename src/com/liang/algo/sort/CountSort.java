package com.liang.algo.sort;

import com.liang.algo.Utils;

import java.util.Arrays;

/**
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 *
 * 1. 计数排序的特征
 * 当输入的元素是 n 个 0 到 k 之间的整数时，它的运行时间是 Θ(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。
 * 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），
 * 这使得计数排序对于数据范围很大的数组，需要大量时间和内存。
 * 例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。
 * 但是，计数排序可以用在基数排序中的算法来排序数据范围很大的数组。
 *
 * 通俗地理解，例如有 10 个年龄不同的人，
 * 统计出有 8 个人的年龄比 A 小，那 A 的年龄就排在第 9 位,
 * 用这个方法可以得到其他每个人的位置,也就排好了序。
 * 当然，年龄有重复时需要特殊处理（保证稳定性），这就是为什么最后要反向填充目标数组，以及将每个数字的统计减去 1 的原因。
 *
 * 2 算法的步骤如下：
 * （1）找出待排序的数组中最大和最小的元素
 * （2）统计数组中每个值为i的元素出现的次数，存入数组C的第i项
 * （3）填充目标数组：根据统计数组，依次填充i, 并移动索引
 */
public class CountSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7};
        CountSort obj = new CountSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] nums) {
        int len = nums.length;

        // find min and max
        int min = nums[0], max = nums[0];
        for (int i = 1; i < len; i ++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }

        // statistics
        int cLen = max - min + 1;
        int[] bucket = new int[cLen];
        for (int n : nums) {
            bucket[n - min] ++;
        }

        // fill
        // 待填充的索引，从0开始
        int index = 0;
        for (int i = 0; i < cLen; i ++) {
            // 从小到大，逐个将bucket的索引即nums的值填入nums中
            while (bucket[i] > 0) {
                nums[index] = i + min;
                bucket[i] --;
                index ++;
            }
        }
    }
}
