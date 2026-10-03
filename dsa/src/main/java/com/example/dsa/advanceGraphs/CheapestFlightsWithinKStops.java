package com.example.dsa.advanceGraphs;

import java.util.Arrays;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int [] visits = new int[n];
        Arrays.fill(visits,Integer.MAX_VALUE);
        visits[src] = 0;
        k++;
        while(k-- > 0){
            int[] temp = visits.clone();

            for(int[] arr : flights){
                int s = arr[0];
                int d = arr[1];
                int cost = arr[2];

                if(visits[s] == Integer.MAX_VALUE)
                    continue;

                if(visits[s] + cost < temp[d]){
                    temp[d] = visits[s] + cost;
                }
            }

            visits = temp;
        }


        if(visits[dst] == Integer.MAX_VALUE)
            return -1;
        return visits[dst];
    }

    public static void main(String[] args) {

        int n = 4;

        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };

        int src = 0;
        int dst = 2;
        int k = 1;

        CheapestFlightsWithinKStops solution =
                new CheapestFlightsWithinKStops();

        int result = solution.findCheapestPrice(n, flights, src, dst, k);

        System.out.println("Cheapest price: " + result);
    }
}