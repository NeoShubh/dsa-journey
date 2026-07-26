package com.example.dsa.binarySearch;

import java.util.Arrays;

public class KoKoEatingBananas875 {
    public static void main(String[] args) {
//        int[] piles = {3, 6, 7, 11};
        int [] piles = {805306368,805306368,805306368};
        int h = 8;
//1,2,3,4,5,6,7,8,9,10,11  1,2,3,4,5,6
        int max = Arrays.stream(piles).max().getAsInt();
        int l = 1;
        int r = max;
        int ans = Integer.MAX_VALUE;
        while (r >= l) {
            int k = l + (r - l) / 2;
            Long hours =(long) 0;
            System.out.println(k);
            for (int i = 0; i < piles.length; i++) {

                hours += Math.ceilDiv(piles[i], k);

            }
            System.out.println(hours);

            if (hours <= h) {
                System.out.println("ans before "+ans);
                ans = Math.min(ans, k);
                System.out.println("ans after "+ans);
                r = k - 1;
            } else l = k + 1;
            System.out.println("______________");
        }
        System.out.println(ans);
    }
}
