package com.example.dsa.arrays.medium;

public class ProductofArrayExceptSelf238 {
    public static void main(String args[]) {

        int[] nums = {1,2,3,4};

        //Another way to solve
        int n = nums.length;
        int pre[] = new int[n];
        int suff[] = new int[n];
        int ans [] = new int [n];
        pre[0] =1;
        suff[n-1] = 1;

        for(int i=1;i<n;i++){
            pre[i] = pre[i-1]*nums[i-1];
        }

        for(int i=n-2;i>=0;i--){
           suff[i] = suff[i+1]*nums[i+1];
        }

        for(int i=0;i<n;i++)
            ans[i] = pre[i]*suff[i];

        for(int i=0;i<n;i++)
            System.out.println(ans[i]);
//
//        int[] answer = new int[4];
//        int mul = Arrays.stream(nums).reduce((a, b) -> a * b
//        ).getAsInt();
//        System.out.println(mul);
//        for (int i = 0; i < answer.length; i++) {
//            answer[i] = mul / nums[i];
//        }
//        for (int i = 0; i < answer.length; i++) {
//            System.out.print(answer[i] + " ");
//        }

    }
}
