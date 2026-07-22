package com.example.dsa.binarySearch;

public class BinarySearch704 {
    public static void main(String [] args){
        int [] nums = {-1,0,3,5,9,12};
        int target =2;

        int left = 0;
        int right = nums.length-1;

        while(right>=left){
            int mid =left+ ((right-left)/2);

            if(nums[mid]==target)
            {
                System.out.println("The "+target+" is present at index "+mid);
                return;
            }
            if(nums[mid]<target)
                left = mid+1;
            if(nums[mid]>target)
                right = mid-1;
        }

    }
}
