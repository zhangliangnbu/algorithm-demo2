package com.liang.algo.common;

/**
 * 941. 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] A) {
        // 遍历，判断先递增后递减
        if (A == null || A.length < 3) {
            return false;
        }
        // 首位特例
        if (A[0] >= A[1] || A[A.length - 1] >= A[A.length - 2]) {
            return false;
        }
        boolean isIncrease = true;
        for (int i = 1; i < A.length; i ++) {
            if (isIncrease && A[i] < A[i-1]) {
                isIncrease = false;
                continue;
            }
            if (!isIncrease && A[i] >= A[i-1]) {
                return false;
            }
        }
        return true;
    }
}
