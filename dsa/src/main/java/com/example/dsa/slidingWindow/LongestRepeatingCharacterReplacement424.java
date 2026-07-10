package com.example.dsa.slidingWindow;

import java.lang.module.FindException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LongestRepeatingCharacterReplacement424 {

    public static void main(String[] args) {
        String s = "ABABBA";
        int k = 1;
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }

    }


}
