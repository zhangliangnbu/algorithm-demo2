package com.liang.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，上面的二进制手表读取 “3:25”。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 示例：
 *
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 *
 * 提示：
 *
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-watch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReadBinaryWatch {

    private List<String> list = new ArrayList<>();
    public List<String> readBinaryWatch(int num) {
        // 选择列表
        int[] nums = {1, 2, 4, 8, 16, 32, 1, 2, 4, 8};
        // 路径 存储索引
        LinkedList<Integer> tracks = new LinkedList<>();
        backtrack(num, nums, tracks);
        return list;
    }

    public void backtrack(int num, int[] nums, LinkedList<Integer> tracks) {
        if (tracks.size() == num) {

            list.add(buildTime(nums, tracks));
            return;
        }
        for (int i = tracks.isEmpty() ? 0 : tracks.getLast(), len = nums.length; i < len; i ++) {
            if (!isValid(i, nums, tracks)) {
                continue;
            }
            tracks.add(i);
            backtrack(num, nums, tracks);
            tracks.removeLast();
        }
    }

    private String buildTime(int[] nums, LinkedList<Integer> tracks) {
        StringBuilder sb = new StringBuilder();
        int hour = 0, mints = 0;
        for (int i : tracks) {
            if (i >=0 && i <= 5) {
                mints += nums[i];
            } else {
                hour += nums[i];
            }
        }
        sb.append(hour);
        sb.append(":");
        if (mints < 10) {
            sb.append(0);
        }
        sb.append(mints);
        return sb.toString();
    }

    private boolean isValid(int index, int[] nums, LinkedList<Integer> tracks) {
        if (tracks.contains(index)) {
            return false;
        }

        int hour = 0, mints = 0;
        for (int i : tracks) {
            if (i >=0 && i <= 5) {
                mints += nums[i];
            } else {
                hour += nums[i];
            }
        }

        if (index >=0 && index <= 5) {
            mints += nums[index];
        } else {
            hour += nums[index];
        }

        return hour <= 11 && mints <= 59;
    }


}
