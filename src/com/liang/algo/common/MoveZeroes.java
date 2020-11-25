package com.liang.algo.common;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 通过次数225,094提交次数359,730
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 0段开始和结束位置
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) {
                end = i;
                if (start == -1) {
                    start = i;
                }
            } else if (end >= 0){
                nums[start] = nums[i];
                nums[i] = 0;
                start ++;
                end ++;
            }
        }
    }
}
