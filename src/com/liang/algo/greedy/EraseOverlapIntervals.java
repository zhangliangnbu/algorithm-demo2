package com.liang.algo.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // 贪心界定：不重叠的最多数量肯定是
        // 每个区间的结束点尽可能小，这样后面的空间就尽可能大，容纳的区间就会尽可能多

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        // 确定目标区间，移除包含目标区间结束点的区间
        int[] start = intervals[0];
        int overlap = 0;
        for (int i = 1; i < intervals.length; i ++) {
            if (intervals[i][0] < start[1]) {
                overlap ++;
            } else {
                start = intervals[i];
            }
        }
        return overlap;
    }
}
