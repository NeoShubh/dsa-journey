package com.example.dsa.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HandOfStraights {
    public static void main(String[] args) {
        int[] hand = new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;

        if (hand.length % groupSize != 0) {
            System.out.println("False");
        }

        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int num : hand) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> pair : mp.entrySet()) {
            pq.offer(pair.getKey());
        }

        while(pq.size()>0){
            if(mp.get(pq.peek())==0){
                pq.poll();
                continue;
            }
            int minVal = pq.peek();
            for(int i=minVal;i<minVal+groupSize;i++){
                if(mp.getOrDefault(i, 0) > 0)
                    mp.put(i,mp.get(i)-1);
                else{
                    System.out.println("false");
                }
            }
        }
        System.out.println(true);
    }
}
