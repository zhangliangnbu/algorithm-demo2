package com.liang.algo.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinStack {

    private LinkedList<Integer> list = new LinkedList<>();
    private int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        list.add(x);
        min = Math.min(min, x);
    }

    public void pop() {
        int rm = list.removeLast();
        if (rm == min) {
            // cal min
            min = Integer.MAX_VALUE;
            for (int val : list) {
                min = Math.min(min, val);
            }
         }
    }

    public int top() {
        return list.getLast();
    }

    public int getMin() {
        return min;
    }
}
