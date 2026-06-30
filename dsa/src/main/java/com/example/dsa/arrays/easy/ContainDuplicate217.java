package com.example.dsa.arrays.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainDuplicate217 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Set<Integer> st = new HashSet<>(Arrays.stream(nums).boxed().toList());
        System.out.println(st.size() == nums.length);
    }
}
