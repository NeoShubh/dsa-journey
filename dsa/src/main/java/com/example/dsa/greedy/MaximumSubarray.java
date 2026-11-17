package com.example.dsa.greedy;

public class MaximumSubarray {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int i = 0, j = 0;
        int sum = 0;
        int maxiMum = Integer.MIN_VALUE;
        while (i <= j && j < arr.length) {

            if (sum < 0) {

                sum = arr[j];
                i = j;
            } else
                sum += arr[j];

            maxiMum = Math.max(maxiMum, sum);

            j++;


        }
        System.out.println(maxiMum);
    }
}
