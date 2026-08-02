package com.example.dsa.contests.contest513;

public class Problem1 {

   static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, Math.abs(a - b));
    }

    public static void main(String[] args) {
        int[] nums = {4,6,8};
        long maxi = Long.MIN_VALUE;

        for (int i = 0; i < nums.length-1; i++) {

            for(int j=1;j<nums.length;j++){

                if(i!=j) {
                    long part1 = (long) (nums[i]*nums[j]);
                    long part2 = gcd((long) nums[i],(long) nums[j]);
                    long ans = part1/(part2*part2);
                     maxi = Math.max(maxi, ans);
                }
            }
        }
        System.out.println(maxi);
    }
}
