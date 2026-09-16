package com.example.dsa.heap;

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
        if (index == s.length() ) {
            ans.add(new ArrayList<>(partition));
            return;
        }

        for(int j=index;j<s.length();j++){
            String substring = s.substring(index, j + 1);
            if(isPalindrome(substring)){
                partition.add(substring);
                dfs(j+1, partition, ans, s);
                partition.removeLast();
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
        List<List<String>> ans = p.partition("abcaa");
        System.out.println(ans);
    }
}
