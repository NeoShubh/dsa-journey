package com.example.dsa.dynamicprogramming.oneD;

import java.util.Arrays;

public class UniquePaths {
    public static void main(String [] args){

        int m =3;
        int n=3;
        int [][] arr = new int [m][n];

        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){

                if(arr[i][j]==0 && i == m-1 || j== n-1){
                    arr[i][j]=1;
                }else{
                    arr[i][j] = arr[i][j+1] + arr[i+1][j];
                }

            }
        }
        System.out.println(arr[0][0]);
    }
}
