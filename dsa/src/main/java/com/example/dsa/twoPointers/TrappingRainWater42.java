package com.example.dsa.twoPointers;

import java.util.Arrays;

//import static java.lang.Math.min;

public class TrappingRainWater42 {
    public static void main(String [] args){
//        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int [] heights = {4,2,0,3,2,5};
        int n = heights.length;
        int [][] arr = new int[3][n];
        int max = 0;

        for(int i=0;i<n-1;i++){
            arr[0][i]= max;
            max=Math.max(max,heights[i]);
        }
//        for(int i=0;i<=n-1;i++){
//            System.out.print(arr[0][i]+" ");
//        }
//        System.out.println();
        max=0;
        for(int i=n-1;i>0;i--){
            arr[1][i]=max;
            max=Math.max(max,heights[i]);
        }
//        for(int i=0;i<=n-1;i++){
//            System.out.print(arr[1][i]+" ");
//        }
        for(int i=0;i<=n-1;i++){

            int ans = Math.min(arr[0][i], arr[1][i]) - heights[i];
            if(ans>0)
                arr[2][i] = ans;
        }
        int sum=0;
        for(int i=0;i<=n-1;i++){
            sum+=arr[2][i];
        }
        System.out.println(sum);
    }
}
