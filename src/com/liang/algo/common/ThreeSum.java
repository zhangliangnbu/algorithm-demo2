package com.liang.algo.common;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        new ThreeSum().threeSum2(nums);
    }

    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        // 回溯
        // 选择列表 非重复的nums
        Arrays.sort(nums);
        // 选择路径
        LinkedList<Integer> track = new LinkedList<>();
         // 递归
        backtrack(nums, track, 0);
        return lists;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, int start) {
        if (track.size() == 3) {
            if (calSum(track) == 0) {
                lists.add(new ArrayList<>(track));
            }
            return;
        }

        for (int i = start; i < nums.length; i ++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track, i + 1);
            track.removeLast();
        }
    }

    private int calSum(List<Integer> list) {
        int sum = 0;
        for (int val : list) {
            sum += val;
        }
        return sum;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i ++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int first = i + 1, last = nums.length - 1;
            while (first < last) {
                if (first > i + 1 && nums[first] == nums[first - 1]) {
                    first ++;
                    continue;
                }
                if (last < nums.length - 1 && nums[last] == nums[last + 1]) {
                    last --;
                    continue;
                }

                if (nums[first] + nums[last] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[first]);
                    list.add(nums[last]);
                    res.add(list);
                    first ++;
                } else if (nums[first] + nums[last] + nums[i] > 0) {
                    last --;
                } else {
                    first ++;
                }
            }
        }

        return res;
    }




}
