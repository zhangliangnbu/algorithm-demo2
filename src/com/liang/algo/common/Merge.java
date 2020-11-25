package com.liang.algo.common;

import java.util.*;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 *
 *
 *
 * 提示：
 *
 * intervals[i][0] <= intervals[i][1]
 */
public class Merge {

    public int[][] merge(int[][] intervals) {
        // sort
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // dc
        LinkedList<int[]> lists = new LinkedList<>();
        for (int[] interval : intervals) {
            if (lists.size() == 0) {
                lists.add(interval);
            } else {
                int[] last = lists.getLast();
                if (interval[0] <= last[1] && interval[1] > last[1]) {
                    lists.getLast()[1] = interval[1];
                } else if (interval[0] > last[1]) {
                    lists.add(interval);
                }
            }
        }

        return lists.toArray(new int[lists.size()][]);
    }
}
