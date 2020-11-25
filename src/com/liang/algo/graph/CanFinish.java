package com.liang.algo.graph;

import java.util.LinkedList;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 *
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 *
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */
public class CanFinish {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 回溯
        // 选择路径 0~n-1
        // 选择列表 路径最后一个点k的连接 从int[i][0]中找到值为k的所有集合
        // 选择限制条件 已经选过的不用选
        LinkedList<Integer> track = new LinkedList<>();

        return false;
    }

    private boolean backtrack(int n, int[][] pre, LinkedList<Integer> track) {
        if (track.size() == n) {
            return true;
        }

        // 找当前可直接修的课程
        for (int i = 0; i < n; i ++) {
            // 当前课程是否可以直接修？


            track.add(i);
            if (backtrack(n, pre, track)) {
                return true;
            }
            track.removeLast();
        }

        return false;
    }

    private boolean canStudy(int n, int[][] pres, LinkedList<Integer> track, int cur) {
        // track 不为空，最后一个课程是cur的前缀
        if (track.size() != 0) {
            int pre = track.getLast();
            int preIndex = getPreIndex(pres, track.getLast(), cur);
        }



        // 已经修过了
        if (track.contains(cur)) {
            return false;
        }



        // 可直接修 无需前置
        return false;
    }

    private int getPreIndex(int[][] pres, int pre, int cur) {
        // cur, pre
        for (int i = 0; i < pres.length; i ++) {
            if (pres[i][0] == cur && pres[i][1] == pre) {
                return i;
            }
        }

        // 不存在先修
        for (int i = 0; i < pres.length; i ++) {
            if (pres[i][0] == cur) {
                return -1;
            }
        }

        return -1;
    }





}
