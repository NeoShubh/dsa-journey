package com.example.dsa.advanceGraphs;

import java.util.*;

public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {

        HashMap<String, PriorityQueue<String>> mp = new HashMap<>();

        // Build adjacency map
        for (List<String> ticket : tickets) {

            String from = ticket.get(0);
            String to = ticket.get(1);

            mp.putIfAbsent(from, new PriorityQueue<>());
            mp.putIfAbsent(to, new PriorityQueue<>());

            mp.get(from).offer(to);
        }

        List<String> results = new ArrayList<>();

        dfs("JFK", mp, results);

        Collections.reverse(results);

        return results;
    }

    void dfs(String current,
             HashMap<String, PriorityQueue<String>> mp,
             List<String> results) {

        while (!mp.get(current).isEmpty()) {

            String next = mp.get(current).poll();

            dfs(next, mp, results);
        }

        results.add(current);
    }

    public static void main(String[] args) {

        List<List<String>> tickets = new ArrayList<>();

        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));

        ReconstructItinerary solution = new ReconstructItinerary();

        List<String> result = solution.findItinerary(tickets);

        System.out.println("Itinerary: " + result);
    }
}