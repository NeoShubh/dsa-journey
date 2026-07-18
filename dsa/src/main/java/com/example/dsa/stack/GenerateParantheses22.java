package com.example.dsa.stack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParantheses22 {
    void fun(int n, int o, int c, String singleVal, List<String> list) {
        if (singleVal.length() == 2 * n) {
            System.out.println("Found: " + singleVal);
            list.add(singleVal);
            return;
        }

        if (o < n) {
            System.out.println("Add ( -> " + singleVal);
            fun(n, o + 1, c, singleVal + "(", list);
        }
        if (c < o) {
            System.out.println("Add ) -> " + singleVal);
            fun(n, o, c + 1, singleVal + ")", list);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        GenerateParantheses22 obj = new GenerateParantheses22();
        List<String> list = new ArrayList<>();
//        List<Character> singleVal = new ArrayList<>();
        String singleVal = "";

        obj.fun(n, 0, 0, singleVal, list);
        System.out.println(list);

    }
}
