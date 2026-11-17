package com.example.dsa.greedy;

public class JumpGame {
    public static void main(String [] args){
//        int arr [] = {2,3,1,1,4};
//        int arr [] = {3,2,1,0,4};
        int arr [] = {2,0,0};
        int x = arr.length-2;
        int goal = arr.length-1;
        while(x>=0){
            if(arr[x]+x >= goal){
                goal=x;
                x--;
            }else{
                x--;
            }
        }
        System.out.println(goal);

    }
}
