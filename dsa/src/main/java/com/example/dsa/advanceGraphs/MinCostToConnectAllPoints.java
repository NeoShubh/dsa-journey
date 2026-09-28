package com.example.dsa.advanceGraphs;


import java.util.*;

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        if (points.length == 1) {
            return 0;
        }
        HashMap<Pair7, List<Pair7>> mp = new HashMap<>();
        HashSet<Pair7> visits = new HashSet<>();
        PriorityQueue<Pair7> pq = new PriorityQueue<>((a, b) -> a.getCost() - b.getCost());
        int answer_cost = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    Pair7 p1 = new Pair7(points[i][0], points[i][1], 0);
                    Pair7 p2 = new Pair7(points[j][0], points[j][1], 0);
                    int cst = Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
                    p2.setCost(cst);
                    mp.putIfAbsent(p1, new ArrayList<>());
                    mp.get(p1).add(p2);
                }
            }
        }
        System.out.println(mp);
        int rounds = points.length - 1;
        System.out.println(rounds);

        Pair7 start = new Pair7(points[0][0], points[0][1], 0);

        visits.add(start);
        List<Pair7> l = mp.get(start);
        pq.addAll(l);
        while (visits.size() < points.length) {
            Pair7 curr = pq.poll();

            if (visits.contains(curr)) {
                continue;
            }

            visits.add(curr);
            answer_cost += curr.getCost();

            pq.addAll(mp.get(curr));
        }
        return answer_cost;
    }

    public static void main(String[] args) {

        int[][] points = {
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };

        MinCostToConnectAllPoints solution =
                new MinCostToConnectAllPoints();

        int result = solution.minCostConnectPoints(points);

        System.out.println("Minimum cost: " + result);
    }
}


class Pair7 {
    private int x;
    private int y;
    private int cost;

    public Pair7(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair7)) return false;

        Pair7 pair = (Pair7) o;

        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pair7{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}