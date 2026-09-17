package com.example.dsa.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        HashMap<Character,String> hp = new HashMap<>();

        hp.put('2',"abc");
        hp.put('3',"def");hp.put('4',"ghi");hp.put('5',"jkl");hp.put('6',"mno");hp.put('7',"pqrs");
        hp.put('8',"tuv");hp.put('9',"wxyz");

        dfs(0,"",ans,digits,hp);
        return ans;
    }
    void dfs(int index, String combination,List<String>ans, String digits, HashMap<Character,String> hp){
        if(combination.length()==digits.length()){
            ans.add(new String(combination));
            return;
        }

        String letters = hp.get(digits.charAt(index));

        for (char c : letters.toCharArray()) {
            dfs(index + 1, combination + c, ans, digits, hp);
        }

    }
    public static void main(String [] args){
        LetterCombinationsofaPhoneNumber obj = new LetterCombinationsofaPhoneNumber();
       List<String> ans = obj.letterCombinations(String.valueOf(23));
        System.out.println(ans);
    }
}
