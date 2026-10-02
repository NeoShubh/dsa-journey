package com.example.dsa.advanceGraphs;

import java.util.HashSet;
import java.util.PriorityQueue;

public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Cell> pq =
                new PriorityQueue<>((a, b) -> a.water_level - b.water_level);
        boolean[][] visited = new boolean[n][n];
        pq.add(new Cell(grid[0][0],0,0));
        visited[0][0] = true;
        int [][]directions = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!pq.isEmpty()){

            Cell C = pq.poll();
            int r = C.row;
            int c = C.col;
            int level = C.water_level;
            if(r==n-1 && c== n-1)
                return level;
            for(int[] arr : directions)
            {
                int new_r = r+arr[0];
                int new_c = c+arr[1];

                if(new_r>= grid.length || new_c >= grid[0].length || new_r<0 || new_c <0 || visited[new_r][new_c]){
                    continue;
                }
                visited[new_r][new_c] = true;
                pq.add(new Cell(Math.max(level,grid[new_r][new_c]),new_r,new_c));
            }

        }

        return 0;
    }



    public static void main(String[] args) {

        int[][] grid = {
                {0, 2},
                {1, 3}
        };

        SwimInRisingWater solution =
                new SwimInRisingWater();

        int result = solution.swimInWater(grid);

        System.out.println("Minimum time: " + result);
    }
}

class Cell {
    int water_level;
    int row;
    int col;



    @Override
    public String toString() {
        return "cell{" +
                "water_level=" + water_level +
                ", row=" + row +
                ", col=" + col +
                '}';
    }

    public  Cell(int water_level, int row, int col) {
        this.water_level = water_level;
        this.row = row;
        this.col = col;
    }
}