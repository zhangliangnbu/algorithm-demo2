package com.liang.algo.common;

import java.util.Stack;

public class CQueue {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public CQueue() {

    }

    public void appendTail(int value) {
        pushStack.push(value);
    }

    public int deleteHead() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        if (popStack.isEmpty()) {
            return -1;
        }

        return popStack.pop();
    }
}
