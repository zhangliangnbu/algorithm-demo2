package com.liang.algo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }

        // n = 1
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        lists.add(list1);
        if (numRows == 1) {
            return lists;
        }
        // n >= 2
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        lists.add(list2);

        for (int i = 2; i < numRows; i ++) {
            // i-1 -> i
            List<Integer> pre = lists.get(i-1);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 0; j < pre.size() - 1; j ++) {
                cur.add(pre.get(j) + pre.get(j+1));
            }
            cur.add(1);
            lists.add(cur);
        }

        return lists;
    }



}
