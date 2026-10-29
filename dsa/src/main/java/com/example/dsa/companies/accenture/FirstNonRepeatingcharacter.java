package com.example.dsa.companies.accenture;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FirstNonRepeatingcharacter {
    public static void main(String [] args){
        String s = "aatddetu";
        int[]visited = new int[26];
        Arrays.fill(visited,-1);
        for(int i=0;i<s.length();i++){
            if(visited[s.charAt(i)-'a'] == -1){
                visited[s.charAt(i)-'a']=i;
            }else{
                visited[s.charAt(i)-'a']=-2;
            }
        }

        for(int i=0;i<s.length();i++){
            if(visited[s.charAt(i)-'a'] >=0){
                System.out.println(s.charAt(i));
                break;
            }
        }
    }
}
