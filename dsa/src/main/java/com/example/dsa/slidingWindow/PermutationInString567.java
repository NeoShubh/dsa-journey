package com.example.dsa.slidingWindow;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class PermutationInString567 {
    public static void main(String[] args) {
//        String s1 = "ab", s2 = "eidbaooo";
        String s1 = "hello", s2 = "ooolleoooleh";
        HashSet<Character> hs = new LinkedHashSet<>();

        for (int i = 0; i < s1.length(); i++)
            hs.add(s1.charAt(i));
        System.out.println(hs);
        boolean flag = false;
        for (int i = 0; i < s2.length(); i++) {
            if (hs.contains(s2.charAt(i))) {
                for (int j = i; j <= i + s1.length() - 1; j++) {
                    if (!hs.contains(s2.charAt(j))) {
                        {
                            flag = false;
                            break;
                        }
                    } else flag = true;
                }
                if (flag) {
                    System.out.println(true);
                }
            }
        }
        System.out.println(flag);
    }
}
