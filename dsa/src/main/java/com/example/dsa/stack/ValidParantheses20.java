package com.example.dsa.stack;

import java.util.HashMap;
import java.util.Stack;

public class ValidParantheses20 {
    public static void main(String [] args){
        String s = "({[()]}[])";
        HashMap<Character,Character> hm = new HashMap<>();
        hm.put(')','(');
        hm.put(']','[');
        hm.put('}','{');
        Stack<Character> stk = new Stack<>();
        for(int i=0;i<s.length();i++){
            System.out.println(i+" times "+s.charAt(i));
            if(hm.containsKey(s.charAt(i))) {
                System.out.println("map contains the key");
                if (!stk.isEmpty() && stk.peek() == hm.get(s.charAt(i))) {

                    System.out.println(stk.pop());
                } else {
                    System.out.println("false");

                }}else
            stk.add(s.charAt(i));
            System.out.println(stk);
        }
    if(stk.isEmpty()){
        System.out.println("True");
    }
    else System.out.println("false");
    }
}
