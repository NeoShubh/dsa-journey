package com.example.dsa.arrays.medium;

import java.util.HashSet;
import java.util.Set;
//The intuition to solve this problem is that imagine it as a game but good thing is that you only have to look after
// the elements which are present So lets say say in board1 you have at index (0,0) = 5 you need to understand
//that that 5 you need to check that is it coming in the same row or column or in the same box of 3x3 if you
// think that yes 5 is not having any of those three condition then we will simply add it in our row, col and box hashet
// So look closely you will see an array of hashset ok not hashset itself so if we create an array of integer
// you will be having a element living on every index but there we will be having a hashset after that we initialize it

//if you did not understand the array of hashset read below three lines
//int[] is like a notebook that comes with 0 already printed on every page. You can write over it directly.
//        HashSet[] is like a shelf with 9 empty slots. The slots exist but there is no notebook placed in them yet.
//        You have to first place a notebook (new HashSet<>()) on each slot before you can write anything in it.

//after that in loop you can see that i is denoting row and j is columns so that for rows and cols hashset we are lookin
//accordingly  but problem arises to find the index for the box so how do you know that this element of this index
//belongs tow which box so for that we have that formula to deal with index = (i/j)*3 + (j/3)
// if we found out the element already existing in these following conditions then it return the false
public class ValidSudoku36 {
    public static void main(String[] args) {
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        HashSet<Character>[] rows    = new HashSet[9];
        HashSet<Character>[] cols    = new HashSet[9];
        HashSet<Character>[] boxes   = new HashSet[9];

        // Sabko initialize karo
        for(int i = 0; i < 9; i++) {
            rows[i]  = new HashSet<>();
            cols[i]  = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board2[i][j];
                if(val == '.') continue;

                int boxIndex = (i/3)*3+(j/3);

                if(rows[i].contains(val))
                {
                    System.out.println("return false "+val);
                    return;
                }
                rows[i].add(val);
                if(cols[j].contains(val))
                {
                    System.out.println("return false "+val);
                    return;
                }
                cols[j].add(val);

                if(boxes[boxIndex].contains(val))
                {
                    System.out.println("return false "+val);
                    return;
                }
                boxes[boxIndex].add(val);
            }
            System.out.println();
        }
        System.out.println("true");

    }
}
