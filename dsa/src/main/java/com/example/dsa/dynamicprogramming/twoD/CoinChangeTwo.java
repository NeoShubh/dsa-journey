package com.example.dsa.dynamicprogramming.twoD;

public class CoinChangeTwo {

    public int change(int amount, int[] coins) {
        int [][] dp = new int[coins.length][amount+1];

        for(int i=0;i< coins.length;i++){
            dp[i][amount]=1;
        }

        for(int i=coins.length-1;i>=0;i--){
            for(int j=amount-1;j>=0;j--){

                if(j+coins[i] <= dp[i].length-1){
                    dp[i][j]+=dp[i][j+coins[i]];
                }
                if(i+1 <= dp.length-1){
                    dp[i][j]+=dp[i+1][j];
                }

                System.out.print(i+" "+j+" "+dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println();
        for(int i=0;i< coins.length;i++){
            for(int j=0;j<=amount;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }



        return dp[0][0];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        CoinChangeTwo obj = new CoinChangeTwo();

        System.out.println(obj.change(amount, coins));
    }
}
