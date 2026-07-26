package com.example.dsa.contests.contest512;

public class Problem1 {
    public static void main(String[] args) {
        int n = 2;
        int s = 9;
//        if(s > n * 9) return -1;
        // edge case
//        if(s == 0) return 0;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int remaining = n - i - 1;  // positions left after current
            int digit = Math.min(9, s - remaining * 0);
            digit = Math.min(9, s);
            digit = Math.max(digit, s - remaining * 9);
            digit = Math.min(digit, 9);
            sb.append(digit);
            s -= digit;
        }
        System.out.println(sb);
//        return Integer.parseInt(sb.toString());

    }
}
