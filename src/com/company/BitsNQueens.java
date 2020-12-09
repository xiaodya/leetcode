package com.company;
//leetcode 52
public class BitsNQueens {
    int counts = 0;
    public int totalNQueens(int n) {
        if (n < 1) return 0;
        //bits solution
        dfs(n,0,0,0,0);
        return this.counts;
    }

    public void dfs(int n, int row,int col,int left,int right){
        if (row >= n){
            this.counts++;
            return;
        }
        //所有能放的bit都为1
        int all_position = (~(col | left | right)) & ((1 << n) - 1);
        while (all_position > 0) {
            //取到最低位的1
            int bit = all_position & (-all_position);
            dfs(n, row + 1, col | bit, (left | bit) << 1, (right | bit) >> 1);
            all_position = all_position&(all_position - 1);

        }
    }

    public static void main(String args[]){
        BitsNQueens bits = new BitsNQueens();
        System.out.println(bits.totalNQueens(4));
    }
}
