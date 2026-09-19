package com.example.dsa.graphs;

public class NumberofIslands200 {

    public int numIslands(char[][] grid) {
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;

        for(int i =0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]== '1'){
                    count++;
                    dfs(i,j,grid);
                }
            }
        }
        return count;
    }
    void dfs(int i, int j, char[][] grid){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]=='0')
            return;
        grid[i][j]='0';
        dfs(i-1,j,grid);
        dfs(i+1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0'}, {'1', '1', '0', '0'}, {'0', '1', '0', '0'}, {'0', '0', '1', '1'},};
        NumberofIslands200 obj = new NumberofIslands200();
        System.out.println(obj.numIslands(grid));
    }
}
