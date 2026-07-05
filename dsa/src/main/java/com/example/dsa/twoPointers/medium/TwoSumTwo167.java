package com.example.dsa.twoPointers.medium;

public class TwoSumTwo167 {
    public static void main(String[] args) {

//        int[] nums = {2, 7, 11, 15};
        int[] nums = {2, 3, 4};
//        int[] nums = {-1,0};
        int target = 7;
        int[] ans = new int[2];

        int left = 0, right = nums.length - 1;
//actual two pointer
//        while (left < right) {
//            int sum = nums[left] + nums[right];
//
//            if (sum == target)
//                {left + 1, right + 1};
//
//            if (sum < target)
//                left++;
//            else
//                right--;
//        }

//       nested loop
        int n = nums.length - 1;
        int i = 0;
        int j = 1;
        while (i <= n - 1) {
            while (j <= n) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i + 1;
                    ans[1] = j + 1;
//                    return;
                }
                j++;
            }
            j = i + 2;
            i++;
        }
//
        for (int num : ans) {
            System.out.print(num + " ");
        }


//        Hashmap solution
//        HashMap<Integer, Integer> mp = new HashMap<>();
//
//        for(int i=0;i<nums.length;i++){
//            if(mp.containsKey(target-nums[i])){
//                ans[0]=mp.get(target-nums[i])+1;
//                ans[1]=i+1;
//            }else mp.put(nums[i],i);
//        }

    }
}
