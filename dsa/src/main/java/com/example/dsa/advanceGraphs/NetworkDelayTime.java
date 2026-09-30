package com.example.dsa.advanceGraphs;

import java.util.*;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {

        // node -> list of (neighbor, time)
        HashMap<Integer, List<Pair>> mp = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            mp.put(i, new ArrayList<>());
        }

        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int cost = time[2];

            mp.get(from).add(new Pair(to, cost));
        }

        // Min heap based on time
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.cost - b.cost);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;
        pq.offer(new Pair(k, 0));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int node = curr.node;
            int time = curr.cost;

            // We already found a shorter path
            if (time > dist[node]) {
                continue;
            }

            for (Pair next : mp.get(node)) {

                int newTime = time + next.cost;

                if (newTime < dist[next.node]) {
                    dist[next.node] = newTime;
                    pq.offer(new Pair(next.node, newTime));
                }
            }
        }

        // Find the time when the last node receives the signal
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }

    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {

        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        int n = 4;
        int k = 2;

        NetworkDelayTime solution = new NetworkDelayTime();

        int result = solution.networkDelayTime(times, n, k);

        System.out.println("Network delay time: " + result);
    }
}
