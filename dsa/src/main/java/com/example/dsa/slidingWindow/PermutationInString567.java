package com.example.dsa.slidingWindow;

import java.util.Arrays;

public class PermutationInString567 {
    public static void main(String[] args) {
//        String s1 = "ab", s2 = "eidbaooo";
        String s1 = "hello", s2 = "ooolleoooleh";
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < 26; i++) {
            arr1[i] = 0;
            arr2[i] = 0;

        }
        int left = 0;
        int right = s1.length();
        for (int i = 0; i < s1.length(); i++) {
            arr2[s2.charAt(i) - 'a']++;
            arr1[s1.charAt(i) - 'a']++;

        }
        if (Arrays.equals(arr1, arr2)) {
            System.out.println(true);
            return;
        }
        while (right <= s2.length() - 1) {
            arr2[s2.charAt(left) - 'a']--;
            left++;
            arr2[s2.charAt(right) - 'a']++;
            right++;
            if (Arrays.equals(arr1, arr2)) {
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }
}
