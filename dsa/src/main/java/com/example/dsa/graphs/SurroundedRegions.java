package com.example.dsa.graphs;

import java.util.Arrays;

public class SurroundedRegions {

    static class Solution {

        public void solve(char[][] board) {

            if (board == null || board.length == 0) {
                return;
            }

            int rows = board.length;
            int cols = board[0].length;

            // Mark all boundary-connected O's as T
            for (int j = 0; j < cols; j++) {
                dfs(0, j, rows, cols, board);
                dfs(rows - 1, j, rows, cols, board);
            }

            for (int i = 0; i < rows; i++) {
                dfs(i, 0, rows, cols, board);
                dfs(i, cols - 1, rows, cols, board);
            }

            // Capture all remaining O's
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dfs1(i, j, rows, cols, board);
                }
            }

            // Convert safe T's back to O's
            for (int j = 0; j < cols; j++) {
                dfs2(0, j, rows, cols, board);
                dfs2(rows - 1, j, rows, cols, board);
            }

            for (int i = 0; i < rows; i++) {
                dfs2(i, 0, rows, cols, board);
                dfs2(i, cols - 1, rows, cols, board);
            }
        }

        // Mark boundary-connected O's as T
        void dfs(int i, int j, int rows, int cols, char[][] board) {

            if (i < 0 || j < 0 ||
                    i >= rows || j >= cols ||
                    board[i][j] == 'X' ||
                    board[i][j] == 'T') {
                return;
            }

            board[i][j] = 'T';

            dfs(i - 1, j, rows, cols, board);
            dfs(i + 1, j, rows, cols, board);
            dfs(i, j - 1, rows, cols, board);
            dfs(i, j + 1, rows, cols, board);
        }

        // Convert remaining O's to X
        void dfs1(int i, int j, int rows, int cols, char[][] board) {

            if (i < 0 || j < 0 ||
                    i >= rows || j >= cols ||
                    board[i][j] == 'X' ||
                    board[i][j] == 'T') {
                return;
            }

            board[i][j] = 'X';

            dfs1(i - 1, j, rows, cols, board);
            dfs1(i + 1, j, rows, cols, board);
            dfs1(i, j - 1, rows, cols, board);
            dfs1(i, j + 1, rows, cols, board);
        }

        // Convert T back to O
        void dfs2(int i, int j, int rows, int cols, char[][] board) {

            if (i < 0 || j < 0 ||
                    i >= rows || j >= cols ||
                    board[i][j] == 'X' ||
                    board[i][j] == 'O') {
                return;
            }

            board[i][j] = 'O';

            dfs2(i - 1, j, rows, cols, board);
            dfs2(i + 1, j, rows, cols, board);
            dfs2(i, j - 1, rows, cols, board);
            dfs2(i, j + 1, rows, cols, board);
        }
    }

    public static void main(String[] args) {

        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        System.out.println("Before:");

        printBoard(board);

        Solution solution = new Solution();
        solution.solve(board);

        System.out.println("\nAfter:");

        printBoard(board);
    }

    static void printBoard(char[][] board) {

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
