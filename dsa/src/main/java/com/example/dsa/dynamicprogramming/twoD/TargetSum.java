package com.example.dsa.dynamicprogramming.twoD;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        int result = dfs(0, 0, nums, target, memo);
        System.out.println("Final memo: " + memo);
        return result;
    }

    int dfs(int index, int sum, int[] nums, int target,
            Map<String, Integer> memo) {
        System.out.println("index "+index);
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        String key = index + "," + sum;
        System.out.println(key);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int add = dfs(index + 1, sum + nums[index], nums, target, memo);
        System.out.println(add);
        int subtract = dfs(index + 1, sum - nums[index], nums, target, memo);
        System.out.println(subtract);

        memo.put(key, add + subtract);
        System.out.println("Cached: " + key + " -> " + memo.get(key));

        return memo.get(key);
    }
//    public int findTargetSumWays(int[] nums, int target) {
//
//
//        return dfs(0, 0, nums, target);
//    }
//
//    int dfs(int index, int sum, int[] nums, int target) {
//        if (index == nums.length) {
//            if (sum == target)
//                return 1;
//            else return 0;
//        }
//
//        int a = dfs(index + 1, sum + nums[index], nums, target);
//
//        int b = dfs(index + 1, sum - nums[index], nums, target);
//
//
//        return a + b;
//    }

    public static void main(String[] args) {
        int[] arr = {1,1};
        int target = 2;
        TargetSum obj = new TargetSum();
        System.out.println(obj.findTargetSumWays(arr, target));
    }
}
