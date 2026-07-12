package com.example.dsa.contests.contest510;

public class ProblemSecond {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
//        int [] nums = {1,1,7,14};
        int k = 4;

        long fixedK = k;
        long bucket = k;
        long refills = 0;
        long cost = 0;
        long MOD = 1_000_000_007;
        long inv2 = 500000004L;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= bucket) {
                bucket -= nums[i];
            } else {
                long shortage = nums[i] - bucket;
                long r = (shortage + fixedK - 1) / fixedK;

                long part1 = (r % MOD) * (refills % MOD) % MOD;
                long part2 = (r % MOD) * ((r + 1) % MOD) % MOD * inv2 % MOD;
                cost = (cost + part1 + part2) % MOD;

                refills += r;
                bucket += r * fixedK;
                bucket -= nums[i];
            }
        }
//        First approach of mine
//        long resources = k;
//        int refills = 0;
//        long cost = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] <= k) {
//                k -= nums[i];
//            } else  {
//                while (nums[i] > k) {
//                    refills++;
//                    cost = cost + refills;
//                    k += resources;
//                }
//                k -= nums[i];
//            }
//        }
        System.out.println(cost% 1_000_000_007);
    }
}
