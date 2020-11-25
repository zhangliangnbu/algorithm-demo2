package com.liang.algo.common;

import sun.reflect.misc.MethodUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 方法一：暴力遍历
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> set = new HashSet<>();
        for (int value : nums1) {
            for (int i : nums2) {
                if (value == i) {
                    set.add(value);
                    break;
                }
            }
        }
        int[] ans = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            ans[index] = iterator.next();
            index ++;
        }
        return ans;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        // 方法一：排序 遍历
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = 0, n2 = 0;
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] == nums2[n2]) {
                set.add(nums1[n1]);
                n1 ++;
                n2 ++;
            } else if(nums1[n1] < nums2[n2]) {
                n1 ++;
            } else {
                n2 ++;
            }
        }

        int[] ans = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            ans[index] = iterator.next();
            index ++;
        }
        return ans;
    }
}
