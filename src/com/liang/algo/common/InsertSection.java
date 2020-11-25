package com.liang.algo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 *
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 */
public class InsertSection {

    public static void main(String[] args) {
        InsertSection is = new InsertSection();
        int[][] intervals = {{1,5}};
        int[] newInterval = {6,8};
        is.insert(intervals, newInterval);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {


        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            int[][] ans = new int[1][2];
            ans[0] = newInterval;
            return ans;
        }
        int row = intervals.length;
        List<int[]> lists = new ArrayList<>();
        int insertStart = row;
        for (int i = 0; i < row; i ++) {
            if (intervals[i][1] < newInterval[0]) {
                // 在插入区间左边
                lists.add(intervals[i]);
            } else if (intervals[i][0] > newInterval[1]) {
                // 在插入区间右边
                lists.add(intervals[i]);
            }
            // 插入区间在中间或与插入区间有交集
            if (intervals[i][1] >= newInterval[0]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                insertStart =Math.min(insertStart, i);
            }
            if (intervals[i][0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        lists.add(insertStart, newInterval);
        return lists.toArray(new int[lists.size()][2]);
    }
}
