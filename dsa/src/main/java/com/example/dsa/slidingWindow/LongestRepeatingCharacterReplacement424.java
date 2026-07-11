package com.example.dsa.slidingWindow;

import java.util.HashMap;

public class LongestRepeatingCharacterReplacement424 {

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 1;
        int left = 0;

        int maxWindow = 0;
        int maxFreq = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int right = 0; right <= s.length() - 1; right++) {

            hm.put(s.charAt(right), hm.getOrDefault(s.charAt(right), 0) + 1);

            maxFreq = Math.max(maxFreq, hm.get(s.charAt(right)));

            if ((right - left + 1) - maxFreq <= k) {

                maxWindow = Math.max(maxWindow, right - left + 1);
            } else {
                hm.put(s.charAt(left), hm.get(s.charAt(left)) - 1);
                left++;
            }
        }
        System.out.println(hm);
        System.out.println(maxFreq);
        System.out.println(maxWindow);
    }


}
