package com.example.dsa.dynamicprogramming.oneD;

import java.util.HashSet;
import java.util.List;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        HashSet st = new HashSet(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            System.out.println("IN J part");
            for (int j = 0; j < i; j++) {


                if (dp[j] && st.contains(s.substring(j, i))) {
                    System.out.println(s.substring(j, i));
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String[] wordDict = {"leet", "code"};
        WordBreak obj = new WordBreak();
        System.out.println(obj.wordBreak(s, List.of(wordDict)));
    }

}
