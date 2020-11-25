package com.liang.algo.common;

import com.liang.algo.Utils;

import java.util.ArrayList;
import java.util.List;

public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] T = {73,74,75,71,69,72,76,73};
        Utils.print(dt.dailyTemperatures(T));
    }

    /**
     * 739. 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */
    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return null;
        }
        int n = T.length;
        int[] ans = new int[n];
        if (n == 0) {
            return ans;
        }
        // 新表里为零的那些索引列表
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            ans[i] = 0;
            zeros.add(i);
            // 更新i之前的值
            int size = zeros.size();
            for (int j = size - 2; j >= 0; j --) {
                int index = zeros.get(j);
                if (T[index] >= T[i]) {
                    break;
                }
                ans[index] = i - index;
                zeros.remove(j);
            }
        }
        return ans;
    }
}
