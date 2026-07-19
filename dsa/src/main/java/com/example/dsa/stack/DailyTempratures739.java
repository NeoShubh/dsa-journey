package com.example.dsa.stack;

import java.util.Stack;

class Pair {
    int value;
    int index;

    Pair(int x, int y) {
        this.value = x;
        this.index = y;
    }
}

public class DailyTempratures739 {
    public static void main(String[] args) {

        int[] tempratures = {73, 74, 75, 71, 69, 72, 76, 73};

        int[] answer = new int[tempratures.length];
        Stack<Pair> stk = new Stack<>();


        for (int i = 0; i < tempratures.length; i++) {
            System.out.println("current element is: " + tempratures[i]);
            if (!stk.isEmpty() && stk.peek().value < tempratures[i]) {
                System.out.println("stk top is less than current temp[i]");
                while (!stk.isEmpty() && stk.peek().value < tempratures[i]) {
                    answer[stk.peek().index] = i - stk.peek().index;
                    for (int num : answer) {
                        System.out.print(num + " ");
                    }
                    System.out.println("removing the element "+stk.peek().value);
                    stk.pop();
                }
            }
            stk.push(new Pair(tempratures[i], i));
            System.out.println("stack after pusing element");
            for (Pair item : stk) {
                System.out.println("|"+item.value + " -> " + item.index+"|");
            }
        }

        for (int num : answer) {
            System.out.print(num + " ");
        }
    }
}
