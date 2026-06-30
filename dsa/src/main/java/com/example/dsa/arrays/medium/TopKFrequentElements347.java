package com.example.dsa.arrays.medium;

import java.util.*;

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class TopKFrequentElements347 {
    public static void main(String[] args) {
        int [] nums ={1,1,1,2,2,3};
        int k = 2;
        Map<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(mp.containsKey(nums[i])){
                mp.put(nums[i],mp.get(nums[i]) + 1);
            }else{
                mp.put(nums[i],1);
            }
        }
//        System.out.println(mp);
        Queue<Pair> q = new PriorityQueue<>(k, (a, b) -> a.second - b.second);
       for(Map.Entry<Integer,Integer> entry : mp.entrySet()){
        q.offer(new Pair(entry.getKey(),entry.getValue()));
        if(q.size()>k){
            q.poll();
        }
       }
        List<Integer> list = new ArrayList<>();
        for(Pair p : q){
//            System.out.println(p.first+" "+p.second);
            list.add(p.first);
        }
        System.out.println(list.reversed());
        int [] ans = list.reversed().stream().mapToInt(Integer::intValue).toArray();
    }
}

