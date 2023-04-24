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
    private int dfs_count = 0;
    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};
    private int max_x,max_y = 0;

    private Queue<Grid> queue = new LinkedList<>();
    private HashSet<Grid> set = new HashSet<>();
    public int numIslands(char[][] grid) {
        this.max_x = grid.length;
        this.max_y = grid[0].length;
        int count = 0;
        for (int i = 0; i<this.max_x; i++){
            for (int j = 0; j<this.max_y; j++){
                if (grid[i][j] == '1'){
                    dfs(i,j,grid);
//                    this.queue.offer(new Grid(i,j));
//                    bfs(this.queue,grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(Queue<Grid> queue, char[][] grid) {
        while (this.queue.size() > 0){
            this.dfs_count++;
            Grid grid1 = queue.poll();
            grid[grid1.i][grid1.j] = '0';
            for (int i=0;i<4;i++){
                int x = grid1.i+dx[i];
                int y = grid1.j+dy[i];
                if (x < 0 || x >= max_x || y < 0 || y >= max_y || grid[x][y] == '0'){
                    continue;
                }else{
                    queue.offer(new Grid(grid1.i+dx[i],grid1.j+dy[i]));
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] grid) {
        this.dfs_count++;
        grid[i][j] = '0';
        for (int k=0;k<4;k++){
            int x = i+dx[k];
            int y = j+dy[k];
            if (x < 0 || x >= max_x || y < 0 || y >= max_y || grid[x][y] == '0'){
                continue;
            }else{
                dfs(x,y,grid);
            }
        }
    }


    public static void main(String args[]){
        char[][] grid = new char[][]{{'1','1','1','0','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','1'}};
        Solution200IsLands solution200IsLands = new Solution200IsLands();
        System.out.println("count="+solution200IsLands.numIslands(grid));
        System.out.println("dfs_count="+solution200IsLands.dfs_count);
    }
}
