package com.example.dsa.slidingWindow;

public class BestTimeToBuyAndSell121 {
    public static void main(String [] args){
//        int [] prices = {7,1,5,3,6,4};
        int[] prices = {1,2,4,2,5,7,2,4,9,0,9};
//        int [] prices = {1,4,2};
//        int [] prices ={3,2,6,5,0,3};
        int left=0;
        int right=left+1;
        int max_ans = Integer.MIN_VALUE;

        while(right<prices.length){
            System.out.println(left+" "+right );
            max_ans= Math.max(max_ans, prices[right]-prices[left]);
            if( prices[right]-prices[left]<=0){
                left=right;
            }
            right++;
        }
        if(max_ans<0)
            max_ans=0;
        System.out.println(max_ans);
    }
}
