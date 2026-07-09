package com.example.dsa.slidingWindow;

import java.util.*;

public class LongestSubstringWithoutRepeatation3 {
    public static void main(String [] args){
        String s = "aabaab!bb";
        if(s.length()==0)
            System.out.println(0);
        LinkedHashSet<Character> st = new LinkedHashSet<>();
//        Queue<Character> q = new LinkedList<>();
        int answer = Integer.MIN_VALUE;
        for(int i=0;i<s.length();i++){
            if(st.contains(s.charAt(i))) {
                Iterator<Character> it = st.iterator();
                while(it.hasNext()) {
                    char val = it.next();
                    it.remove();              // remove current element
                    if(val == s.charAt(i)) break;  // stop AFTER removing the duplicate
                }
            }
                st.add(s.charAt(i));
                answer = Math.max(answer,st.size());
        }
        System.out.println(answer);
    }
}
