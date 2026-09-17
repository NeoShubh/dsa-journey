package com.example.dsa.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {


    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> partition = new ArrayList<>();
        dfs(0, partition, ans, s);
        return ans;
    }

    void dfs(int index, List<String> partition, List<List<String>> ans, String s) {
        System.out.println("index is "+index);
        if (index == s.length() ) {
            System.out.println("we got it");
            ans.add(new ArrayList<>(partition));
            return;
        }

        for(int j=index;j<s.length();j++){
            System.out.println(index+" - "+j);
            String substring = s.substring(index, j + 1);
            System.out.println(substring);
            if(isPalindrome(substring)){
                partition.add(substring);
                dfs(j+1, partition, ans, s);
//                System.out.println();
                System.out.println("what we remove is "+ partition.removeLast());
            }
        }

    }

    boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }


    public static void main(String[] args) {
        PalindromePartitioning p = new PalindromePartitioning();
        List<List<String>> ans = p.partition("aab");
        System.out.println(ans);
    }
}
