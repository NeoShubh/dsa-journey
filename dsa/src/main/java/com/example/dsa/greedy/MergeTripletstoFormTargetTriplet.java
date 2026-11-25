package com.example.dsa.greedy;

import java.util.ArrayList;
import java.util.List;

public class MergeTripletstoFormTargetTriplet {
    public static void main(String args[]) {
        int[][] triplets = new int[][]{{2, 5, 3}, {1, 8, 4}, {1, 7, 5}};
        int[] target = new int[]{2, 7, 5};
        boolean a = false, b = false, c = false;
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < triplets.length; i++) {
            if (triplets[i][0] > target[0] || triplets[i][1] > target[1] || triplets[i][2] > target[2]) {
                continue;
            } else {
                if (triplets[i][0] == target[0])
                    a = true;
                if (triplets[i][1] == target[1])
                    b = true;
                if (triplets[i][2] == target[2])
                    c = true;

            }
        }
        if (a && b && c) System.out.println("True");
        else System.out.println("False");

    }
}
