package com.liang.algo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        // 思路:取一个，检查一个，继续
        if (S == null || S.length() == 0) {
            return new ArrayList<>();
        }

        int n = S.length();
        List<Integer> list = new ArrayList<>();
        int pp = 0, p = 0, w;
        while (p < n) {
            pp = p;
            // 取
            char c = S.charAt(p);
            p = S.lastIndexOf(c);

            // 检查p-lastIndex
            w = pp + 1;
            while (w < p) {
                int wC = S.charAt(w);
                int last = S.lastIndexOf(wC);
                p = Math.max(last, p);
                w ++;
            }

            list.add(p - pp + 1);
            p ++;
        }

        return list;
    }

}
