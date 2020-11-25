package com.liang.algo.common;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 */
public class Trap {

    public static void main(String[] args) {
        Trap t = new Trap();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] height = {4,2,3};
        System.out.println(t.trap(height));
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 找出左右开始可以盛水的点
        int start = height.length - 1, end = 0;
        for (int i = 1; i < height.length; i ++) {
            if (height[i] < height[i-1]) {
                start = i - 1;
                break;
            }
        }
        for (int i = height.length - 2; i > 0; i --) {
            if (height[i] < height[i + 1]) {
                end = i + 1;
                break;
            }
        }

        if (start >= end) {
            return 0;
        }

        // 计算水量
        int sum = 0;
        for (int i = start; i < end; ) {
            // i - end之间最大值
            int nearIndex = findNearIndex(height, i, end);
            int h = Math.min(height[i], height[nearIndex]);
            // cal
            for (int j = i + 1; j < nearIndex; j ++) {
                sum += Math.max(0, h - height[j]);
            }
            i = nearIndex;
        }

        return sum;

    }

    // start-1 ~ end中寻找最近大于等于start的，没有的话就返回最大的
    private int findNearIndex(int[] height, int start, int end) {
        int maxIndex = start + 1;
        for (int i = start + 1; i <= end; i ++) {
            if (height[i] >= height[start]) {
                return i;
            }
            if (height[i] > height[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
