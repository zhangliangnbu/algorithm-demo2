package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {
        new RestoreIpAddresses().restoreIpAddresses("010010");
    }

    private List<String> list = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        // 决策点选择：'.' 插入s中的位置 index后 每次最多三位
        // 路径列表
        LinkedList<Integer> track = new LinkedList<>();
        // 回溯
        backtrack(s, track, 0);
        return list;
    }

    private void backtrack(String s, LinkedList<Integer> track, int startIndex) {
        if (track.size() == 3) {
            list.add(buildIPStr(s, track));
            return;
        }
        for (int i = 0; i < 3; i ++) {
            if (!isValid(s, track, startIndex, i)) {
                continue;
            }
            track.add(startIndex + i);
            backtrack(s, track, startIndex + i + 1);
            track.removeLast();
        }
    }

    private boolean isValid(String s, LinkedList<Integer> track, int startIndex, int step) {
        // 超过范围
        if (startIndex + step >= s.length() - 1) {
            return false;
        }

        // 前导零
        if (s.charAt(startIndex) == '0' && step >= 1) {
            return false;
        }

        // 三个数时 和判断
        if (step == 2 && Integer.parseInt(s.substring(startIndex, startIndex + 3)) > 255) {
            return false;
        }

        // 最后一个点 判断
        if (track.size() == 2) {
            String end = s.substring(startIndex + step + 1);
            if (end.length() > 3) {
                return false;
            }
            if (end.length() > 1 && (end.startsWith("0") || Integer.parseInt(end) > 255)) {
                return false;
            }
        }

        return true;
    }

    private String buildIPStr(String s, LinkedList<Integer> track) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < s.length(); i ++) {
            sb.append(s.charAt(i));
            if (j < track.size() && i == track.get(j)) {
                sb.append('.');
                j ++;
            }
        }
        return sb.toString();
    }

}
