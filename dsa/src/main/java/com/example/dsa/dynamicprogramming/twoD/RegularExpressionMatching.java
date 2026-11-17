package com.example.dsa.dynamicprogramming.twoD;

public class RegularExpressionMatching {


    public boolean isMatch(String s, String p) {

//        return dfs(0, 0, s, p);
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(0, 0, s, p, dp);
    }

    boolean dfs(int i, int j, String s, String p, Boolean dp[][]) {
        if (dp[i][j] != null) return dp[i][j];
        if (i >= s.length() && j >= p.length()) return true;
        if (j >= p.length()) return false;

        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            dp[i][j] = dfs(i, j + 2, s, p,dp) || (match && dfs(i + 1, j, s, p,dp));
            return dp[i][j];
        }
        if (match) {
            dp[i][j] = dfs(i + 1, j + 1, s, p,dp);
            return dp[i][j];
        }
        dp[i][j]=false;
        return dp[i][j];
    }

    boolean dfs(int i, int j, String s, String p) {
        if (i >= s.length() && j >= p.length()) return true;
        if (j >= p.length()) return false;

        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            return dfs(i, j + 2, s, p) || (match && dfs(i + 1, j, s, p));
        }
        if (match) return dfs(i + 1, j + 1, s, p);
        return false;


    }


    public static void main(String args[]) {
        RegularExpressionMatching obj = new RegularExpressionMatching();
        System.out.println(obj.isMatch("aab", "c*a*b"));
    }
}
