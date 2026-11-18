package com.example.dsa.greedy;

public class JumpGameTwo {
    public static void main(String [] args){
        int [] arr = {2,3,1,1,4};

        int l =0, r = 0;int res=0;
        while(r<arr.length-1){
            int farthest = 0;

            for(int i=l;i<=r;i++){
                farthest = Math.max(farthest,i+arr[i]);
            }

            l=r+1;
            r=farthest;
            res+=1;

        }
        System.out.println(res);
    }
}
