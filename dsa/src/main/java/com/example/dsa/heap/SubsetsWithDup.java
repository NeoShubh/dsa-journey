package com.example.dsa.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDup {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        dfs(0, ans, subset, nums);

        return ans;
    }

    static void dfs(int index, List<List<Integer>> ans,
                    List<Integer> subset, int[] nums) {

        if (index == nums.length) {
            ans.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[index]);

        dfs(index + 1, ans, subset, nums);

        subset.remove(subset.size() - 1);

        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index += 1;
        }

        dfs(index + 1, ans, subset, nums);
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 2};

        List<List<Integer>> result = subsetsWithDup(nums);

        System.out.println(result);
    }
}
