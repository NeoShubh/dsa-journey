package com.example.dsa.binarySearch;

public class MinInRotatedSortedArray153 {
    public static void main(String [] args){
        int [] nums = {4,0,1,2,3};

        int right = nums.length-1;
        int left = 0;

        int ans = Integer.MAX_VALUE;

        while(right>left){



            int mid = left + (right-left)/2;

            ans = Math.min(ans,nums[mid]);

            if(nums[mid]>=nums[left]){
                left = mid + 1;
            }
            else
                right = mid ;
        }
        System.out.println(ans);
    }
}
