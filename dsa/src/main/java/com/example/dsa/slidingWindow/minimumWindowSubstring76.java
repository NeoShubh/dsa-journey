package com.example.dsa.slidingWindow;

import java.util.HashMap;

public class minimumWindowSubstring76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";

        int left = 0;
        int have = 0;
        int minWindow = Integer.MAX_VALUE;
        HashMap<Character, Integer> hm1 = new HashMap<>();
        HashMap<Character, Integer> hm2 = new HashMap<>();
String answer = "";
        int start = 0;
        for (int i = 0; i < t.length(); i++) {
            hm1.put(t.charAt(i), hm1.getOrDefault(t.charAt(i), 0) + 1);
        }
        int need = hm1.size();


        for (int right = 0; right < s.length(); right++) {

            hm2.put(s.charAt(right), hm2.getOrDefault(s.charAt(right), 0) + 1);
            if (hm1.containsKey(s.charAt(right)) && hm2.get(s.charAt(right)) == hm1.get(s.charAt(right))) {
                have++;
            }
            if(have >= need) {


                while(have >= need) {
//                    minWindow = Math.min(minWindow, right - left + 1); // keep only this

                    if (right - left + 1 < minWindow) {
                        minWindow = right - left + 1;
                        start = left;
                    }
                    if(hm1.containsKey(s.charAt(left)) &&
                            hm2.get(s.charAt(left)) <= hm1.get(s.charAt(left))) {
                        have--;
                    }
                    hm2.put(s.charAt(left), hm2.get(s.charAt(left)) - 1);
                    left++;
                }
            }

        }
        if (minWindow == Integer.MAX_VALUE) {
            System.out.println("");
        } else {
            System.out.println(s.substring(start, start + minWindow));
        }
    }
}
