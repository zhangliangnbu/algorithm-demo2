package com.liang.algo.backtrack;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermuteUnique {
    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) {
            return lists;
        }

        // 选择列表: 去重的nums - 已经选完的数字; 使用map 记录重复数字的数量 key-数字，value-重复数量
        Map<Integer, Integer> map = buildMap(nums);
        // 路径列表
        LinkedList<Integer> track = new LinkedList<>();
        // 回溯
        backtrack(map, track, nums.length);
        return lists;
    }

    private void backtrack(Map<Integer, Integer> map, LinkedList<Integer> track, int len) {
        if (track.size() == len) {
            lists.add(new ArrayList<>(track));
            return;
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            map.put(entry.getKey(), entry.getValue() - 1);
            track.add(entry.getKey());
            backtrack(map, track, len);
            int key = track.removeLast();
            map.put(key, map.get(key) + 1);
        }
    }

    private Map<Integer, Integer> buildMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        return map;
    }
}
