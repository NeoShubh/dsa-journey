package com.example.dsa.stack;

import java.util.Stack;

public class MinStack155 {
    Stack<Integer> stk;
    Stack<Integer> minStk;
    int minValue = Integer.MAX_VALUE;
    public MinStack155() {
        stk = new Stack<Integer>();
        minStk = new Stack<Integer>();
    }

    public void push(int value) {
        stk.push(value);
        if(!minStk.isEmpty())
        minValue = Math.min(value,minStk.peek() );
        else minValue = value;
        minStk.push(minValue);
    }

    public void pop() {
        stk.pop();
        minStk.pop();
    }

    public int top() {
return stk.peek();
    }

    public int getMin() {
return minStk.peek();
    }

    public static void main(String[] args) {
        MinStack155 obj = new MinStack155();
        obj.push(4);
        obj.push(5);
        obj.push(2);
        obj.push(9);
        obj.push(7);
        obj.push(1);
        System.out.println(obj.minStk);
        System.out.println(obj.stk);
        obj.pop();
        System.out.println(obj.minStk);
        System.out.println(obj.stk);
        int param_3 = obj.top();
        System.out.println(obj.minStk);
        System.out.println(obj.stk);
        int param_4 = obj.getMin();
        System.out.println(obj.minStk);
        System.out.println(obj.stk);
    }
}
