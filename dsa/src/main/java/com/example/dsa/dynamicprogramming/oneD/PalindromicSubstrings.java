package com.example.dsa.dynamicprogramming.oneD;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++){
            ans+= helper(s,i,i);
            ans+= helper(s,i,i+1);
        }
        return ans;
    }

    int helper(String s, int start, int end){
        int res = 0;
        while(start>=0 && end<s.length() && s.charAt(start)==s.charAt(end)){
            res++;
            start--;
            end++;
        }
        return res;
    }
    public static void main(String[] args){
     PalindromicSubstrings obj = new PalindromicSubstrings();
        System.out.println(obj.countSubstrings("aaa"));
    }
}
