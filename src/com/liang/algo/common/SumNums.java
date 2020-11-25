package com.liang.algo.common;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 */
public class SumNums {
    public int sumNums(int n) {
        boolean flag = (n > 0) && (n += sumNums(n-1)) > 0;
        return n;
    }
}
