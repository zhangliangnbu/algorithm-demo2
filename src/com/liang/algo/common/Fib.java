package com.liang.algo.common;

public class Fib {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 0, cur = 1, temp;
        for (int i = 2; i <= n; i ++) {
            temp = cur;
            cur = (cur + pre) % 1000000007;
            pre = temp;
        }
        return cur;
    }
}
