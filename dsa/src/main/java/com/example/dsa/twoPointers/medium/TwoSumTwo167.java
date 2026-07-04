package com.example.dsa.twoPointers.medium;

import java.util.HashMap;

public class TwoSumTwo167 {
    public static void main(String[] args) {

//        int[] nums = {2, 7, 11, 15};
//        int[] nums = {2,3,4};
        int[] nums = {-1,0};
        int target = -1;
        int [] ans = new int[2];
        HashMap<Integer, Integer> mp = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(mp.containsKey(target-nums[i])){
                ans[0]=mp.get(target-nums[i])+1;
                ans[1]=i+1;
            }else mp.put(nums[i],i);
        }
    for(int num:ans){
        System.out.print(num+" ");
    }
    }
}
