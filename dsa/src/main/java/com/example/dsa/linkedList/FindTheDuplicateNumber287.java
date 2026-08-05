package com.example.dsa.linkedList;

import java.util.HashMap;
import java.util.Map;

public class FindTheDuplicateNumber287 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
//        int slow =0;
//        int fast=0;
//        while(true){
//            slow = nums[slow];
//            fast=nums[nums[fast]];
//            if(slow==fast)
//                break;
//        }
//        int slow2= 0;
//        while(true){
//            slow = nums[slow];
//            slow2=nums[slow2];
//            if(slow==slow2)
//                System.out.println(slow);
////                return slow;
//        }

        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(hm.containsKey(nums[i])){
                hm.put( nums[i],hm.get(nums[i])+1);
            }else{
                hm.put(nums[i],1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if(entry.getValue()>1)
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
