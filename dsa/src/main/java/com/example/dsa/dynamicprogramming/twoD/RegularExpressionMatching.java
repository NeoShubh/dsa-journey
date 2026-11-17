package com.example.dsa.dynamicprogramming.twoD;

public class RegularExpressionMatching {


    public boolean isMatch(String s, String p) {

        return dfs(0, 0, s, p);
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
        System.out.println(obj.isMatch("a", ".*c"));
    }
}
