package com.liang.algo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;
        List<Integer> list = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                list.add(nums1[p1]);
                p1 ++;
                p2 ++;
            } else if (nums1[p1] < nums2[p2]) {
                p1 ++;
            } else {
                p2 ++;
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i ++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
