package com.example.dsa.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumTwo {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0, ans, lst, candidates, target);

        return ans;
    }

    public void dfs(int i, List<List<Integer>> ans,
                    List<Integer> lst, int[] nums, int target) {

        if (target == 0) {
            ans.add(new ArrayList<>(lst));
            return;
        }

        if (i >= nums.length || target < 0) {
            return;
        }

        lst.add(nums[i]);
        dfs(i + 1, ans, lst, nums, target - nums[i]);

        lst.remove(lst.size() - 1);
        while (i + 1 < nums.length && nums[i] == nums[i + 1])
            i += 1;
        dfs(i + 1, ans, lst, nums, target);
    }
    public static void main(String[] args) {
        CombinationSumTwo obj = new CombinationSumTwo();

        List<List<Integer>> ans = obj.combinationSum2(new int[]{2, 1,1,1,3}, 5);
        System.out.println(ans);
    }
}
