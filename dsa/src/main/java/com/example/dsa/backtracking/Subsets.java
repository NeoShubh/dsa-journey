package com.example.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        dfs(0,ans,lst,nums);
        return ans;
    }

    public void dfs(int i,List<List<Integer>> ans, List<Integer> lst, int[]nums){
        if(i>=nums.length){
            ans.add(new ArrayList<Integer>(lst));
            return;
        }

        lst.add(nums[i]);
        dfs(i+1,ans,lst,nums);
        lst.remove(lst.size() - 1);
        dfs(i+1,ans,lst,nums);

    }

    public static void main(String [] args){
        Subsets obj = new Subsets();

        List<List<Integer>> ans = obj.subsets(new int[]{1, 2,3});
        System.out.println(ans);
    }
}