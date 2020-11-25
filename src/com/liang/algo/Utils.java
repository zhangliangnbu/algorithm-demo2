package com.liang.algo;

public class Utils {

    public static void print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int val : arr) {
            sb.append(val);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
