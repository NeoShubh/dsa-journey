package com.example.dsa.dynamicprogramming.oneD;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int one=1;
        int two = 1;

        for(int i=n;i>=1;i--){
            int temp = one;
            one = one + two;
            two = temp;
        }
        System.out.println(one+" "+two);
        return two;
    }

    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();
        System.out.println(obj.climbStairs(2));
    }
}
