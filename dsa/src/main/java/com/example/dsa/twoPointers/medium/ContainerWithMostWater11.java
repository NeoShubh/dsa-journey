package com.example.dsa.twoPointers.medium;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] heights = {1, 1};
        int i = 0;
        int j = heights.length - 1;
        int ans = 0;

        while (i < j) {
            int min = Math.min(heights[i], heights[j]);
            ans = Math.max((j - i) * min, ans);

            // move the smaller height pointer
            //jaha choti side waha se aage badho with every move
            if (heights[i] < heights[j]) {
                i++;
            } else {
                j--;
            }
        }
        System.out.println(ans);
//        int i = 0;
//        int j = 1;
//        int ans = 0;
//        int n = heights.length;
//        while (i <= n - 2) {
//            while (j <= n - 1) {
//                System.out.println("heights[" + i + "]: " + heights[i] + " heights[" + j + "]: " + heights[j]);
//                int min = Math.min(heights[i], heights[j]);
//                System.out.println("min :" + min);
//                ans = Math.max((j - i) * min, ans);
//                System.out.println("ans :" + ans);
//                j++;
//            }
//            i++;
//            j=i+1;
//        }

//        System.out.println(ans);
    }
}
