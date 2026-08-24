package com.example.dsa.contests.contest516;

public class ProblemOne {
    public static void main(String[] args) {
        String s = "ff";
        StringBuilder originalStr = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            String binary = String.format(
                    "%8s",
                    Integer.toBinaryString(s.charAt(i))
            ).replace(' ', '0');

            originalStr.append(binary);
        }
        System.out.println(isPalindrome(String.valueOf(originalStr)));
    }

  public static boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (j >= i) {
            if (s.charAt(j) != s.charAt(i)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
