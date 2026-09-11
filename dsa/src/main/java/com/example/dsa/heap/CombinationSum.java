package com.example.dsa.heap;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        dfs(0, ans, lst, candidates, target,0);
        return ans;
    }

    public void dfs(int i, List<List<Integer>> ans, List<Integer> lst, int[] nums, int target,int currentSum) {
        if (i >= nums.length) {
            int sum = lst.stream().reduce((a, b) -> a + b).get();
            if (!lst.isEmpty()) {
                if (sum == target) {
                    ans.add(new ArrayList<>(lst));
                }
            }
            if(sum>target)
                return;
            return;
        }

        lst.add(nums[i]);
        dfs(i, ans, lst, nums, target,currentSum);

        lst.remove(lst.size() - 1);
        dfs(i + 1, ans, lst, nums, target,currentSum);

    }

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();

        List<List<Integer>> ans = obj.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(ans);
    }
}
