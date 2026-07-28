package com.example.dsa.binarySearch;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MedianOfTwoSorted4 {
    public static void main(String [] args){
        int [] arr1 = {1,3};
        int [] arr2 = {2,4};

        int [] ans = IntStream.concat(Arrays.stream(arr1),Arrays.stream(arr2)).toArray();
        Arrays.sort(ans);
        int n = ans.length;
        if(n%2==0){
            System.out.println((ans[n/2]  + ans[(n/2) -1])/2.0 );
        }
        else{
            System.out.println(ans[(n/2)]);
        }
    }
}
