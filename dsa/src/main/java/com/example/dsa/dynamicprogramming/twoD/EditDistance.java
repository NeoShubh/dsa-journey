package com.example.dsa.dynamicprogramming.twoD;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];


        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    if (i == m && j == n) {
                        dp[i][j] = 0;
                    }
                    if (i == m && j != n) {
                        dp[i][j] = dp[i][j + 1]+1;
                    }
                    if (j == n && i != m) {
                        dp[i][j] = dp[i+1][j]+1;
                    }
                } else if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1]));

                }

            }
        }


        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        System.out.println(obj.minDistance("horse", "ros"));
    }
}
