package com.example.dsa.graphs;

import java.util.*;

public class PacificAtlanticWaterFlow {

    static class Solution {

        public List<List<Integer>> pacificAtlantic(int[][] heights) {

            int rows = heights.length;
            int cols = heights[0].length;

            List<List<Integer>> ans = new ArrayList<>();

            boolean[][] pac = new boolean[rows][cols];
            boolean[][] atl = new boolean[rows][cols];

            // Top and bottom rows
            for (int j = 0; j < cols; j++) {
                dfs(0, j, heights, pac, heights[0][j]);
                dfs(rows - 1, j, heights, atl, heights[rows - 1][j]);
            }

            // Left and right columns
            for (int i = 0; i < rows; i++) {
                dfs(i, 0, heights, pac, heights[i][0]);
                dfs(i, cols - 1, heights, atl, heights[i][cols - 1]);
            }

            // Cells that can reach both oceans
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    if (pac[i][j] && atl[i][j]) {
                        List<Integer> cell = new ArrayList<>();
                        cell.add(i);
                        cell.add(j);
                        ans.add(cell);
                    }
                }
            }

            return ans;
        }

        void dfs(int i, int j, int[][] heights,
                 boolean[][] visited, int prevHeight) {

            if (i < 0 || j < 0 ||
                    i >= heights.length ||
                    j >= heights[0].length ||
                    visited[i][j] ||
                    prevHeight > heights[i][j]) {
                return;
            }

            visited[i][j] = true;

            int currHeight = heights[i][j];

            dfs(i + 1, j, heights, visited, currHeight);
            dfs(i - 1, j, heights, visited, currHeight);
            dfs(i, j + 1, heights, visited, currHeight);
            dfs(i, j - 1, heights, visited, currHeight);
        }
    }

    public static void main(String[] args) {

        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        Solution solution = new Solution();

        List<List<Integer>> result =
                solution.pacificAtlantic(heights);

        System.out.println("Cells that can reach both oceans:");

        for (List<Integer> cell : result) {
            System.out.println(cell);
        }
    }
}