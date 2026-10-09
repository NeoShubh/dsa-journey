package com.example.dsa.dynamicprogramming.oneD;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j <= coins.length - 1; j++) {
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        System.out.println(dp[amount]);
        if(dp[amount] > amount || dp[amount]<0)
            return -1;
        else return dp[amount];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5};
        int target = 11;
        CoinChange obj = new CoinChange();
        System.out.println(obj.coinChange(arr, target));
    }
}
