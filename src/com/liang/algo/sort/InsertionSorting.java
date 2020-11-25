package com.liang.algo.sort;

import com.liang.algo.Utils;
import com.liang.algo.common.InsertSection;

public class InsertionSorting {

    public static void main(String[] args) {
        InsertionSorting obj = new InsertionSorting();
        int[] nums = {9,8,4,2,1,3,5,6,7};
        obj.sort(nums);
        Utils.print(nums);
    }

    // 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i ++) {
            // 将无序区元素nums[i]插入到0~i-1的有序区域里
            int temp = nums[i], j = i;
            while (j > 0 && nums[j-1] > temp) {
                nums[j] = nums[j-1];
                j--;
            }
            if (j != i) {
                nums[j] = temp;
            }
        }
    }
}
