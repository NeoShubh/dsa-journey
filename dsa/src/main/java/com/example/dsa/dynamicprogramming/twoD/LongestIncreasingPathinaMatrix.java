package com.example.dsa.dynamicprogramming.twoD;

public class LongestIncreasingPathinaMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxValue = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxValue = Math.max(maxValue, dfs(i, j, matrix, dp, Integer.MIN_VALUE));
            }
        }
        return maxValue;
    }

    int dfs(int row, int col, int[][] matrix, int[][] dp, int prevValue) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] <= prevValue) {
            return 0;
        }
        if (dp[row][col] > 0) return dp[row][col];

        int res = 1;
        res = Math.max(res, 1 + dfs(row - 1, col, matrix, dp, matrix[row][col]));
        res = Math.max(res, 1 + dfs(row + 1, col, matrix, dp, matrix[row][col]));
        res = Math.max(res, 1 + dfs(row, col + 1, matrix, dp, matrix[row][col]));
        res = Math.max(res, 1 + dfs(row, col - 1, matrix, dp, matrix[row][col]));
        dp[row][col] = res;

        return res;
    }

    public static void main(String[] args) {
        LongestIncreasingPathinaMatrix obj = new LongestIncreasingPathinaMatrix();
        int[][] matrix = {{2,6}, {1,9}};
        System.out.println(obj.longestIncreasingPath(matrix));
    }
}
