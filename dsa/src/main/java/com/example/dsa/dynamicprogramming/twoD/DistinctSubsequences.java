package com.example.dsa.dynamicprogramming.twoD;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for(int j=1;j<dp[0].length;j++){
        dp[0][j]=0;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){

                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
            System.out.println();
        }

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j] +" ");
            }
            System.out.println();
        }
        return dp[m][n];
    }

    public static void main(String [] args){
        DistinctSubsequences obj = new DistinctSubsequences();
        System.out.println(obj.numDistinct("bbb","bb"));
    }
}
