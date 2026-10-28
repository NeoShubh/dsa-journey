package com.example.dsa.companies.accenture;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Anagram {
    public static void main(String [] args){
        String w1 = "abc";
        String w2 = "abc";

        HashMap<Character,Integer> mp1 = new HashMap<>();
        HashMap<Character,Integer> mp2 = new HashMap<>();

        for(int i=0;i<w1.length();i++){


            mp1.put(w1.charAt(i),mp1.getOrDefault(w1.charAt(i),0)+1);
        }
        for(int i=0;i<w2.length();i++){
            mp2.put(w2.charAt(i),mp2.getOrDefault(w2.charAt(i),0)+1);
        }
        System.out.println(mp1);
        System.out.println(mp2);

        for(Map.Entry<Character, Integer> entry : mp1.entrySet()){
            if(mp2.containsKey(entry.getKey()) && Objects.equals(mp2.get(entry.getKey()), entry.getValue())){

            }else{
                System.out.println("false");
            }
        }

    }
}
