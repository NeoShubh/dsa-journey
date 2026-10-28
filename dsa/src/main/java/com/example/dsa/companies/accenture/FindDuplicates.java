package com.example.dsa.companies.accenture;

import java.util.*;

public class FindDuplicates {
    public static void main(String [] args){
        int[] arr = {2,2,3,1,1,4};

        Map<Integer,Integer> mp = new HashMap<>();

        for(int i=0;i<arr.length;i++){
            mp.putIfAbsent(arr[i],mp.getOrDefault(arr[i],0)+1);
        }
        System.out.println(mp);

//        int max = Integer.MIN_VALUE;
//        for(int i=0;i<arr.length;i++){
//            max = Math.max(max,arr[i]);
//        }
//        System.out.println(max);
//        List<Integer> ans = new ArrayList<>();
//        boolean[]visited = new boolean[max+1];
//        Arrays.fill(visited,false);
//
//        for(int i=0;i<arr.length;i++){
//            if(visited[arr[i]]){
//                ans.add(arr[i]);
//            }else{
//                visited[arr[i]]=true;
//            }
//        }
//        for(int i=0;i<visited.length;i++){
//            System.out.println(i+" "+visited[i]);
//        }
//        System.out.println(ans);
    }
}
