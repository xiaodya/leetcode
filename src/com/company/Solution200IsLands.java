package com.company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution200IsLands {
    class Grid{
        int i=0;
        int j=0;
        public Grid(int i,int j){
            this.i = i;
            this.j = j;
        }
    }
    public int dfs_count = 0;
    private Queue<Grid> queue = new LinkedList<>();
    private HashSet<Grid> set = new HashSet<>();
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[0].length; j++){
                if (grid[i][j] == '1'){
                    count++;
//                    dfs(i,j,grid);
                    this.queue.offer(new Grid(i,j));
                    bfs(this.queue,grid);
                }
            }
        }
        return count;
    }

    private void bfs(Queue<Grid> queue, char[][] grid) {
        while (this.queue.size() > 0){
            this.dfs_count++;
            Grid grid1  = queue.poll();
            grid[grid1.i][grid1.j] = '0';
            if (grid1.i<grid.length-1 && grid[grid1.i+1][grid1.j] == '1') queue.offer(new Grid(grid1.i+1,grid1.j));
            if (grid1.i>0 && grid[grid1.i-1][grid1.j] == '1') queue.offer(new Grid(grid1.i-1,grid1.j));
            if (grid1.j<grid[0].length-1 && grid[grid1.i][grid1.j+1] == '1') queue.offer(new Grid(grid1.i,grid1.j+1));
            if (grid1.j>0 && grid[grid1.i][grid1.j-1]=='1') queue.offer(new Grid(grid1.i,grid1.j-1));
        }
    }

    private void dfs(int i, int j, char[][] grid) {
        this.dfs_count++;
        grid[i][j] = '0';
        if (i<grid.length-1 && grid[i+1][j] == '1') dfs(i+1,j,grid);
        if (i>0 && grid[i-1][j] == '1') dfs(i-1,j,grid);
        if (j<grid[0].length-1 && grid[i][j+1] == '1') dfs(i,j+1,grid);
        if (j>0 && grid[i][j-1]=='1') dfs(i,j-1,grid);
    }


    public static void main(String args[]){
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        Solution200IsLands solution200IsLands = new Solution200IsLands();
        System.out.println("count="+solution200IsLands.numIslands(grid));
        System.out.println("dfs_count="+solution200IsLands.dfs_count);
    }
}
