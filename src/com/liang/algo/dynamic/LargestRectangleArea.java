package com.liang.algo.dynamic;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class LargestRectangleArea {

    public static void main(String[] args) {

        int[] numst = {2,1,5,6,2,3};
        LargestRectangleArea obj = new LargestRectangleArea();
        System.out.println(obj.largestRectangleArea(numst));

    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        // 以heights[i]为高度的左矩形
        int[] left = new int[n];
        left[0] = heights[0];
        for (int i = 1; i < n; i ++) {
            if (heights[i] == 0) {
                continue;
            }
            if (heights[i] == heights[i-1]) {
                left[i] = left[i-1] + heights[i];
            } else if (heights[i] > heights[i-1]) {
                left[i] = heights[i];
            } else {
                int j = i - 1, area = heights[i];
                while (j >= 0 && heights[j] >= heights[i]) {
                    area += (left[j] / heights[j]) * heights[i];
                    j -= (left[j] / heights[j]);
                }
                left[i] = area;
            }
        }

        // 以heights[i]为高度的右矩形
        int[] right = new int[n];
        right[n-1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i --) {
            if (heights[i] == 0) {
                continue;
            }
            if (heights[i] == heights[i+1]) {
                right[i] = right[i+1] + heights[i];
            } else if (heights[i] > heights[i+1]) {
                right[i] = heights[i];
            } else {
                int j = i + 1, area = heights[i];
                while (j < n && heights[j] >= heights[i]) {
                    area += (right[j] / heights[j]) * heights[i];
                    j += (right[j] / heights[j]);
                }
                right[i] = area;
            }
        }

        // 计算以heights[i]为高度的矩形面积
        int max = 0;
        for (int i = 0; i < n; i ++) {
            max = Math.max(max, left[i] + right[i] - heights[i]);
        }

        return max;
    }

    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        // mins[j] i~j间的最小高度
        int[] mins = new int[n];
        int max = heights[0];
        for (int i = 0; i < n; i ++) {
            for (int j = i; j < n; j ++) {
                if (i == j) {
                    mins[j] = heights[j];
                    max = Math.max(max, heights[j]);
                } else {
                    mins[j] = Math.min(mins[j-1], heights[j]);
                    max = Math.max(max, (j - i + 1) * mins[j]);
                }
            }
        }

        return max;
    }

}
