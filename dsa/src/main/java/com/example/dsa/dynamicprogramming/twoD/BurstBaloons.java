package com.example.dsa.dynamicprogramming.twoD;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BurstBaloons {
    public int maxCoins(int[] nums) {
        int[] a = {1};
        int n = nums.length;
        int[] c = IntStream.concat(Arrays.stream(a), Arrays.stream(nums)).toArray();
        int[] arr = IntStream.concat(Arrays.stream(c), Arrays.stream(a)).toArray();
        System.out.println(arr.length);
int dp[][] = new int [n+2][n+2];

       for(int gaps=2;gaps<=n+1;gaps++){
           for(int i=0;i+gaps<=n+1;i++){
               int j=i+gaps;
               for(int k=i+1;k<j;k++){
                    dp[i][j] = Math.max(dp[i][j],arr[i]*arr[k]*arr[j]+dp[i][k] + dp[k][j] );
               }
           }
       }
        return dp[0][n+1];
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 8};
        BurstBaloons obj = new BurstBaloons();
        System.out.println(obj.maxCoins(arr));
    }
}
