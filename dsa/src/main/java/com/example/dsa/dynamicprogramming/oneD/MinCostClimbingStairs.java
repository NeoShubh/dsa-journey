package com.example.dsa.dynamicprogramming.oneD;

import java.util.Arrays;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {

        int[] newArr = Arrays.copyOf(cost, cost.length + 1);
        for (int i = newArr.length - 3; i >= 0; i--) {
            newArr[i] += Math.min(newArr[i + 1], newArr[i + 2]);
        }
        for(int i:newArr){
            System.out.println(i);
        }
        return Math.min(newArr[0], newArr[1]);
//        int x = cost.length;
//        int n = x;
//        int ans = 0;
//        while(x>0){
//            if(x == 1)
//                break;
//
//            int first = x-1;
//            int second = x-2;
//
//            if(first>=0 || second>=0){
//                if(second>=0){
//                    if(cost[first]<cost[second]){
//                        ans+=cost[first];
//                        x--;
//                    }else{
//                        ans+=cost[second];
//                        x-=2;
//                    }
//                }else if(first >= 0 && second <0){
//                    ans+=cost[first];
//                    x--;
//                }
//            }
//        }
//        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1};
        MinCostClimbingStairs obj = new MinCostClimbingStairs();
        System.out.println(obj.minCostClimbingStairs(arr));
    }
}
