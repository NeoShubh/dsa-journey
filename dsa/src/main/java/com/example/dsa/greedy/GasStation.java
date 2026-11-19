package com.example.dsa.greedy;

public class GasStation {
    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int sum = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {

            tank += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }

        }
        if (sum < 0) System.out.println(-1);
        else
            System.out.println(start);

    }
}
