package com.liang.algo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 */
public class FindContinuousSequence {

    public static void main(String[] args) {
        FindContinuousSequence fcs = new FindContinuousSequence();
        fcs.findContinuousSequence(9);

        int[][] a = {{1,2},{1,2,3}};
        System.out.println(a.length);
    }

    public int[][] findContinuousSequence(int target) {
        // 双指针
        if (target == 1 || target == 2) {
            return new int[0][0];
        }

        List<int[]> ls = new ArrayList<>();
        int start = 1, end = 2, last = target / 2 + 1;
        int sum;
        while (start < end && end <= last) {
            sum = (start + end)*(end - start + 1)/2;
            if (sum == target) {
                ls.add(buildArr(start, end));
                start ++;
                end ++;
            } else if (sum > target) {
                start ++;
            } else {
                end ++;
            }
        }

        // ls to add
        return ls.toArray(new int[ls.size()][]);
    }

    private int[] buildArr(int start, int end) {
        int[] arr = new int[end - start + 1];
        for (int i = start; i <= end; i ++) {
            arr[i-start] = i;
        }
        return arr;
    }

    private List<Integer> buildList(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i ++) {
            list.add(i);
        }
        return list;
    }
}
