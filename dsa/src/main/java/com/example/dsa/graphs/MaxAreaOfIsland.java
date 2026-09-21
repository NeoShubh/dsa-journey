package com.example.dsa.graphs;

import java.util.Arrays;

public class MaxAreaOfIsland {

    public static class Solution {

        public int maxAreaOfIsland(int[][] grid) {

            int row = grid.length;
            int col = grid[0].length;
            int maxi = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {

                    if (grid[i][j] == 1) {
                        maxi = Math.max(maxi, dfs(i, j, grid));
                    }
                }
            }

            return maxi;
        }

        int dfs(int i, int j, int[][] grid) {

            if (i < 0 || j < 0 ||
                    i >= grid.length ||
                    j >= grid[0].length ||
                    grid[i][j] == 0) {
                return 0;
            }

            // Mark as visited
            grid[i][j] = 0;

            int a = dfs(i - 1, j, grid);
            int b = dfs(i + 1, j, grid);
            int c = dfs(i, j - 1, grid);
            int d = dfs(i, j + 1, grid);

            return 1 + a + b + c + d;
        }
    }

    public static void main(String[] args) {

        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Solution solution = new Solution();

        int result = solution.maxAreaOfIsland(grid);

        System.out.println("Maximum area of island: " + result);
    }
}