package com.example.dsa.stack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParantheses22 {
    String fun(int n, int o, int c, String ans) {
        System.out.println(n + " " + o + " " + c);
        System.out.println(ans);

        if (o == 0 && c == 0)
            return ans;

        if (o < n && c >= o) {
            ans.concat("(");
            fun(n, --o, c, ans);
        } else {
            ans.concat(")");
            fun(n, o, --c, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 3;
        GenerateParantheses22 obj = new GenerateParantheses22();
        List<String> list = new ArrayList<>();
        int o = 3, c = 3;
        String ans = "";

        System.out.println(obj.fun(n, o, c, ans));


    }
}
