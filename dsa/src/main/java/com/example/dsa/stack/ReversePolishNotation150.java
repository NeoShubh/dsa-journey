package com.example.dsa.stack;

import java.util.Stack;

public class ReversePolishNotation150 {
    public static void main(String [] args){
       String [] s = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
       Stack<String> stk = new Stack<>();
       for(int i=0;i<s.length;i++){
           if(s[i].equals("+") || s[i].equals("-") || s[i].equals("*") || s[i].equals("/")){
              int firstNum = Integer.parseInt(stk.pop());
               int secondNum = Integer.parseInt(stk.pop());
               System.out.println(firstNum+" "+secondNum);
               if(s[i].equals("+")) {
                   int answer = firstNum + secondNum;
                    stk.add(String.valueOf(answer));
               }else  if(s[i].equals("-")) {
                   int answer = firstNum - secondNum;
                   stk.add(String.valueOf(answer));
               }else  if(s[i].equals("*")) {
                   int answer = firstNum * secondNum;
                   stk.add(String.valueOf(answer));
               }else  if(s[i].equals("/")) {
                   int answer = secondNum / firstNum;
                   stk.add(String.valueOf(answer));
               }
           }
           else{
               stk.add(s[i]);
           }
       }
        System.out.println(stk);
    }
}
