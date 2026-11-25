package com.example.dsa.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        Map<Character, Integer> mp = new HashMap<>();
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
           mp.put(s.charAt(i),i);
        }

        for(Map.Entry<Character,Integer> pair : mp.entrySet()){
            System.out.println(pair.getKey()+" "+pair.getValue());
        }
        int end = 0;
        int start = 0;
        for(int i=0;i<s.length();i++){
           end = Math.max(end, mp.get(s.charAt(i)));
           if(i==end){
               lst.add(end - start + 1);
               start = end + 1;
           }
        }
        System.out.println(lst);
    }
}
