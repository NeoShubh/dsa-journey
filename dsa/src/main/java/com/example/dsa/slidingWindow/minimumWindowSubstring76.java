package com.example.dsa.slidingWindow;

import java.util.HashMap;
import java.util.Objects;
//In Java, HashMap stores values as Integer objects, not int primitives.
// When you use == to compare two Integer objects, Java compares their memory addresses,
// not their actual values. Java internally caches Integer objects only for
// values between -128 to 127, so for values in that range == accidentally works
// because both references point to the same cached object. But for values greater than
// 127, Java creates a new Integer object every time, so two Integer objects holding
// the same value like 200 will have different memory addresses, making == return false
// even though their values are equal. This is why in the Minimum Window Substring problem,
// when character frequencies in the HashMap exceeded 127 — which happens with large input
// strings — the condition hm2.get(c) == hm1.get(c) was silently returning false, have was
// never incrementing correctly, and the valid window was never captured. The fix is to
// always use .equals() for comparing Integer objects since it compares the actual value
// stored inside the object, not the memory address, and works correctly for all values
// regardless of range.

//In my words
// Last test case was failing due 46 line we were using == in our if condition instead of equals
// basically if you have two INTEGER objects not the primitive int then they store the values at
//some memory location but the value should be lying in -128 to 127 because lets say you have two
//integer objects a and b both are having 100 value its lying in between the range so the value will
//be storing at some memory location and both a and b will be pointing to that memory location if you use
//== that for sure you will get true in that condition but beyond that lets say 128 in both variables now
//two different memory location will be allocated to both variables now even if the values are same == return
//false java does cache the integer objects in that range but beyond that it creates a new integer object
//to avoid this situation use equals always dealing with non premitive . == compare on the basis of memory address
// and equals compare on the basis of actual values stored in it most likely because of last test case
// our hashmap will be carrying the frequencies more than 127
public class minimumWindowSubstring76 {
    public static void main(String[] args) {
//        String s = "ADOBECODEBANC", t = "ABC";
//        String s = "a", t = "a";
        String s = "a", t = "aa";
//        if(t.length()>s.length())
//            return;

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
            if (hm1.containsKey(s.charAt(right)) && Objects.equals(hm2.get(s.charAt(right)), hm1.get(s.charAt(right)))) {
                have++;
            }
            if (have >= need) {
                while (have >= need) {
                    // update window BEFORE shrinking
                    if (right - left + 1 < minWindow) {
                        minWindow = right - left + 1;
                        start = left;
                    }

                    hm2.put(s.charAt(left), hm2.get(s.charAt(left)) - 1);

                    // check AFTER decrementing freq
                    if (hm1.containsKey(s.charAt(left)) &&
                            hm2.get(s.charAt(left)) < hm1.get(s.charAt(left))) {
                        have--;
                    }
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
