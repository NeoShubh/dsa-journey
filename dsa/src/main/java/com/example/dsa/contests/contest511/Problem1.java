package com.example.dsa.contests.contest511;

public class Problem1 {
    public static void main(String [] args){
        int [] start = {1,1};
        int [] end = {2,2};
        int s = start[0]+start[1];
        int e = end[0]+end[1];
        if(s%2==0 && e%2==0 || e%2!=0 && s%2!=0)
            System.out.println("true");
        else System.out.println("false");

    }
}
