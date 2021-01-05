package com.liang.algo;

/**
 * 564. 寻找最近的回文数
 * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。
 *
 * “最近的”定义为两个整数差的绝对值最小。
 *
 * 示例 1:
 *
 * 输入: "123"
 * 输出: "121"
 * 注意:
 *
 * n 是由字符串表示的正整数，其长度不超过18。
 * 如果有多个结果，返回最小的那个。
 * 通过次数3,174提交次数18,723
 */
public class NearestPalindromic {

    public static void main(String[] args) {
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println(String.valueOf(Long.MIN_VALUE).length());
        System.out.println(String.valueOf(Integer.MIN_VALUE).length());
    }

    public String nearestPalindromic(String n) {
        // 长度不超过18 用long可以表示

        // n为个位数
        // 判断n是否为回文数
        int len = n.length();
        StringBuilder sb = new StringBuilder();
        if (isPalindromic(n)) {
            // 是回文数 改变中间的一个或两个数字
            if (len % 2 == 1) {
                char mc = n.charAt(len / 2);
                int mi = Character.digit(mc, 10);
                if (mi == 0) {
                    mi = 1;
                } else {
                    mi -= 1;
                }
                sb.append(n, 0, len / 2);
                sb.append(mi);
                sb.append(n, len / 2 + 1, len - 1);
            } else {
                String ms = n.substring(len / 2 - 1, len / 2 + 1);
                int mi = Integer.parseInt(ms);
                if (len == 2 && mi == 11) {
                    mi = 9;
                } else if (mi == 0) {
                    mi = 11;
                } else {
                    mi -= 11;
                }
                sb.append(n, 0, len / 2 - 1);
                sb.append(mi);
                sb.append(n, len / 2 + 1, len - 1);
            }
        } else {
            // 不是回文数 改变后半部分的数字
            if (n .equals("10")) {

            }
        }



        return sb.toString();
    }

    private boolean isPalindromic(String n) {
        if (n.length() == 1) {
            return true;
        }
        for (int i = 0, mid = n.length() / 2; i < mid; i ++) {
            if (n.charAt(i) != n.charAt(n.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
