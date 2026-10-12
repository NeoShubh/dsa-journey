package com.example.dsa.dynamicprogramming.oneD;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        int currMax = nums[0];
        int currMin = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int newMax = Integer.max(nums[i], currMax * nums[i]);
            newMax = Integer.max(newMax, currMin * nums[i]);
            int newMin = Integer.min(nums[i], currMin * nums[i]);
            newMin = Integer.min(newMin, currMax * nums[i]);
            currMin = newMin;
            currMax = newMax;
            result = Integer.max(currMax,result);
        }
    return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -2,4};
        MaxProduct obj = new MaxProduct();
        System.out.println(obj.maxProduct(arr));
    }
}
