package com.liang.algo;

/**
 * 面试题 10.01. 合并排序的数组
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 * 说明:
 *
 * A.length == n + m
 * 通过次数38,104提交次数70,128
 */
public class Merge {

    public void merge(int[] A, int m, int[] B, int n) {
        // 最大值放在最后
        int pa = m - 1, pb = n - 1, index = m + n - 1;
        while (pa >= 0 && pb >= 0) {
            if (A[pa] > B[pb]) {
                A[index] = A[pa];
                pa --;
            } else {
                A[index] = B[pb];
                pb --;
            }
            index --;
        }

        while (pb >= 0) {
            A[index] = B[pb];
            pb --;
            index --;
        }

        while (pa >= 0) {
            A[index] = A[pa];
            pa --;
            index --;
        }
    }


}
