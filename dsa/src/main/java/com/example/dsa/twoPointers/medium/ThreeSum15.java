package com.example.dsa.twoPointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    public static void main(String[] args) {
//        int numbers[] = {-1, 0, 1, 2, -1, -4}; //-4 -1 -1 0 1 2
//        int numbers[] = {0,1,1};
//        int numbers[] ={0,0,0};
//        int numbers [] = {0,0,0,1,2,0,0};
        int nums[] = {-2,0,1,1,2};
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for(int i = 0;i < n - 2;i++){
            int j = i+1,k = n - 1;

            if(i > 0 && nums[i] == nums[i-1]) continue;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j-1]) j++;
                }else if(sum < 0) j++;
                else k--;

            }
        }

        System.out.println(res);
    }
}
