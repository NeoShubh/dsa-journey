package com.example.dsa.dynamicprogramming.oneD;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String ans = "";

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            String odd = helper(s, i, i);

            // Even length palindrome
            String even = helper(s, i, i + 1);

            if (odd.length() > ans.length()) {
                ans = odd;
            }

            if (even.length() > ans.length()) {
                ans = even;
            }
        }

        return ans;
    }

    String helper(String s, int left, int right) {

        while (left >= 0 &&
                right < s.length() &&
                s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
    public static void main(String [] args){
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        System.out.println(obj.longestPalindrome("babad"));
    }
}
