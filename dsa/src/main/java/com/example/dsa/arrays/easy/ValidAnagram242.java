package com.example.dsa.arrays.easy;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    public static void main(String[] args) {
//        String s = "anagram", t = "nagaram";
        String s = "a", t = "ab";

//        if(s.length() != t.length()) return false;
        HashMap<Character, Integer> mp = new HashMap<>();
        HashMap<Character, Integer> tp = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (mp.containsKey(s.charAt(i)))
                mp.put(s.charAt(i), mp.get(s.charAt(i)) + 1);
            else
                mp.put(s.charAt(i), 1);
        }
        for (int i = 0; i < t.length(); i++) {
            if (tp.containsKey(t.charAt(i)))
                tp.put(t.charAt(i), tp.get(t.charAt(i)) + 1);
            else
                tp.put(t.charAt(i), 1);
        }
        System.out.println(mp);
        System.out.println(tp);

        for(Map.Entry entry : mp.entrySet() ){
            System.out.println(entry.getKey() +" : "+entry.getValue());

            if(tp.containsKey(entry.getKey()) &&  tp.get(entry.getKey()).equals(entry.getValue())){

            }else{
                System.out.println("hey");
            }
        }


    }
}
