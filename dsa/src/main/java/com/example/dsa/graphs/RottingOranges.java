package com.example.dsa.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {



        public int RottingOranges(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;

            int minutes = 0;
            Deque<int[]> dq = new ArrayDeque<>();
            int fresh_oranges = 0;

            // 1. Traverse grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        fresh_oranges++;
                    }
                    if (grid[i][j] == 2) {
                        int[] arr = new int[2];
                        arr[0] = i;
                        arr[1] = j;
                        dq.offer(arr);
                    }
                }
            }

            int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

            while (!dq.isEmpty() && fresh_oranges > 0) {

                // Number of rotten oranges at the start of this minute
                int size = dq.size();

                // Process only this level
                for (int i = 0; i < size; i++) {

                    int[] pair = dq.poll();

                    // Check 4 directions
                    for (int[] direction : directions) {

                        int new_x = pair[0] + direction[0];
                        int new_y = pair[1] + direction[1];

                        if (new_x < 0 || new_y < 0 ||
                                new_x >= rows || new_y >= cols ||
                                grid[new_x][new_y] == 0 ||
                                grid[new_x][new_y] == 2) {
                            continue;
                        }

                        // Fresh orange becomes rotten
                        grid[new_x][new_y] = 2;
                        fresh_oranges--;

                        // Add it for the next minute
                        dq.offer(new int[]{new_x, new_y});
                    }
                }

                minutes++;
            }

            return minutes;

        }

        public static void main(String[] args) {

            int[][] grid = {
                    {2, 1, 1},
                    {1, 1, 0},
                    {0, 1, 1}
            };

            RottingOranges solution = new RottingOranges();

            int result = solution.RottingOranges(grid);

            System.out.println("Minutes: " + result);
        }
    }
