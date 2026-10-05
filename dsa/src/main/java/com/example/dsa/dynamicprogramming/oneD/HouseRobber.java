package com.example.dsa.dynamicprogramming.oneD;

public class HouseRobber {
    public int rob(int[] nums) {
        int [] dp = new int[nums.length];

        dp[0]=nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for (int i : dp)
            System.out.print(i+"- ");
        System.out.println();
        for(int i=2;i<=nums.length-1;i++){
            dp[i] = Math.max(nums[i]+dp[i-2],dp[i-1]);
         }

        for (int i : dp)
            System.out.println(i);
        return 0;
    }

    public static void main(String [] args){
        HouseRobber obj = new HouseRobber();
        int [] arr = {2,1,1,2};
        System.out.println(obj.rob(arr));
    }
}
