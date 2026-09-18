package com.example.dsa.backtracking;

import java.util.*;

public class NQueen {

        public List<List<String>> solveNQueens(int n) {

            Set<Integer> col = new HashSet<>();
            Set<Integer> posDiag = new HashSet<>(); // r + c
            Set<Integer> negDiag = new HashSet<>(); // r - c

            List<List<String>> res = new ArrayList<>();

            char[][] board = new char[n][n];

            for (int r = 0; r < n; r++) {
                Arrays.fill(board[r], '.');
            }

            backtrack(0, n, board, col, posDiag, negDiag, res);

            return res;
        }

    void backtrack(int r, int n,
                   char[][] board,
                   Set<Integer> col,
                   Set<Integer> posDiag,
                   Set<Integer> negDiag,
                   List<List<String>> res) {

        if (r == n) {

            List<String> copy = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                copy.add(new String(board[i]));
            }

            res.add(copy);
            return;
        }

        for (int c = 0; c < n; c++) {

            if (col.contains(c)
                    || posDiag.contains(r + c)
                    || negDiag.contains(r - c)) {
                continue;
            }

            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);

            board[r][c] = 'Q';

            backtrack(r + 1, n, board,
                    col, posDiag, negDiag, res);

            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);

            board[r][c] = '.';
        }
    }

        public static void main(String [] args){
            NQueen obj = new NQueen();
            System.out.println(  obj.solveNQueens(4));
        }

}
