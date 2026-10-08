package com.example.dsa.dynamicprogramming.oneD;

public class DecodeWays {
    public int numDecodings(String s) {
        int arr[] = new int[s.length() + 1];
        arr[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i)=='0')continue;
            arr[i]=arr[i+1];
            if (i + 1 < s.length() &&
                    (s.charAt(i) == '1' ||
                            (s.charAt(i) == '2' && s.charAt(i + 1) <= '6'))) {
                arr[i] += arr[i + 2];
            }
        }

        return arr[0];

    }

    public static void main(String[] args) {
        String s = "226";
        DecodeWays obj = new DecodeWays();
        System.out.println(obj.numDecodings(s));
    }
}
