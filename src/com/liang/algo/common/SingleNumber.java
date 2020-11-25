package com.liang.algo.common;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 通过次数286,062提交次数407,792
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        // 方法一 使用map
        // 方法二 位运算 异或 a^0 = a, a^a = 0
        int sum = 0;
        for (int val : nums) {
            sum = sum ^ val;
        }
        return sum;

    }
}
