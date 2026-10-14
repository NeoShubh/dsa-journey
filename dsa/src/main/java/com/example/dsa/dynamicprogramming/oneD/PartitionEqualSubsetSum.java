package com.example.dsa.dynamicprogramming.oneD;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) return false;

        int target = total / 2;
        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);

        for (int i = nums.length - 1; i >= 0; i--) {
            Set<Integer> newSums = new HashSet<>();

            for (int element : hs) {
                newSums.add(element + nums[i]);
            }

            hs.addAll(newSums);

            if (hs.contains(target)) return true;
        }

        return false;


//        int total = Arrays.stream(nums).sum();
//        if (total % 2 != 0) return false;
//
//        int half = total / 2;
//        return dfs(0, nums, half);
//
//    }
//
//    boolean dfs(int currIndex, int[] nums, int half) {
//        if (half == 0) return true;
//        if (currIndex >= nums.length || half < 0) return false;
//
//        boolean take = dfs(currIndex + 1, nums, half - nums[currIndex]);
//        boolean skip = dfs(currIndex + 1, nums, half);
//
//        return take || skip;
    }

    public static void main(String[] args) {
        int[] arr = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        System.out.println(obj.canPartition(arr));
    }
}
