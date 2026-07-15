package com.example.dsa.slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum239 {
    public static void main(String [] args){
        int [] nums = {1,3,-1,-3,5,3,6,7};
        int k=3;
        int [] answer = new int[nums.length-k+1];
        Deque<Integer> dq = new ArrayDeque<>();
        int minWindow = 0;
        for(int right=0;right<nums.length;right++){
            System.out.println(right+" times");

            if(!dq.isEmpty() && dq.peekFirst() < right - k + 1) {
                dq.pollFirst();
            }

            // STEP 2 — remove from BACK if smaller than current element
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[right]) {
                dq.pollLast();
            }

            // STEP 3 — add current index to back
            dq.addLast(right);

            if(right >= k - 1) {
                answer[right - k + 1] = nums[dq.peekFirst()];
            }
            System.out.println(dq);
        }
        for(int i=0;i<answer.length;i++)
            System.out.println(answer[i]+" ");
//        My first approach containing the time complexity of n^2 where We are iterating over the nums and
//        picking up the first and second highest number from the nums So that we can maintain the answer array
//        and get the highest number from the current window we might be removing one by one that is why maintaining
//        second highest would be the good idea but we will get TLE that is why we will be choosing the deque technique
//      int l = 0;
//      int r = 0;
//      int firstHighest = Integer.MIN_VALUE;
//        int secondMax = Integer.MIN_VALUE;
//        int [] answer = new int[nums.length-2];
//      while(r<k){
//          if (nums[r] > firstHighest) {
//              // Shift the old highest down to second highest
//              secondMax = firstHighest;
//              firstHighest = nums[r];
//          } else if (nums[r] > secondMax && nums[r] != firstHighest) {
//              // Update second highest only if it's unique
//              secondMax = nums[r];
//          }
//          r++;
//      }
//      answer[0]=firstHighest;
//        System.out.println(firstHighest+" "+secondMax);
//      l++;
//        for(int right=k;right<=nums.length-1;right++){
//
//        }
    }
}
