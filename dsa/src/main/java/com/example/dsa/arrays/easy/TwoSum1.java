package com.example.dsa.arrays.easy;

import java.util.HashMap;

public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;


//        int i = 0, j = 1;
//        int[] numbers = new int[2];
//        boolean flag = false;
//        while (i <= nums.length - 1 && flag == false) {
//            j=i+1;
//            while (j <= nums.length-1) {
//                System.out.println("the i and j are "+i+" "+j);
//                if(nums[i]+nums[j]==target)
//                {
//                    System.out.println("match found");
//                    numbers[0] = i;
//                    numbers[1] = j;
//                    return;
//                }
//                j++;
//            }
//            i++;
//        }

//        Second way
        HashMap<Integer,Integer> mp = new HashMap<>();
        int[] numbers = new int[2];
        for(int i=0;i<nums.length;i++){
            if(mp.containsKey(target-nums[i]))
            {
                numbers[0] = mp.get(target-nums[i]);
                numbers[1] = i;

                System.out.println("we found the match"+numbers[0]+" "+numbers[1]);
            }
            mp.put(nums[i],i);
        }

    }
}
