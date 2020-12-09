package com.company;

import java.util.*;
//leetcode 51
public class NQueens {
    /**
     * 皇后可以攻击竖着的横着的,左斜线,右斜线的对手.
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0) return result;
        Set<Integer> pie = new HashSet<>();
        Set<Integer> na = new HashSet<>();
        dfs(0,n,pie,na,new HashSet<>(),result,new ArrayList<>());
        return result;
    }

    private void dfs(int level, int n, Set<Integer> pie, Set<Integer> na,Set<Integer> col,List<List<String>> results, List<String> tempList) {
        if (level > n - 1) {
            if (tempList.size() == n){
                List<String> result = new ArrayList<>(tempList);
                results.add(result);
            }
            return;
        }

//        boolean exists = false;
        // 遍历列
        for (int j = 0; j < n; j++) {
            if (pie.contains(level + j) || na.contains(j - level) || col.contains(j)) {// 会被攻击的位置!
                continue;
            }

//            exists = true;

            pie.add(level + j);
            na.add(j - level);
            col.add(j);

            String temp = getStr(n,j);

            tempList.add(temp);

            dfs(level + 1,n,pie,na,col,results,tempList);

            pie.remove(level + j);
            na.remove(j - level);
            col.remove(j);
            tempList.remove(temp);
        }
//        if (!exists){
//            return;
//        }
    }

    private String getStr(int n, int j) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == j){
                stringBuilder.append("Q");
            }else
                stringBuilder.append(".");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        NQueens solution = new NQueens();

        System.out.println(solution.solveNQueens2(4));
        System.out.println(solution.solveNQueens(4));
    }

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] board = new int[n][n];
//        System.out.println(Arrays.deepToString(board));
        //初始化默认赋值全0，不用手动赋值
        solveNQueensUtil(board , n , 0,result); //board , total_space , currRow
        return result;
    }

    public void storeBoardIntoResult(int[][] board,int n,List<List<String>> result){
        List<String> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            StringBuilder str = new StringBuilder("");
            for(int j=0;j<n;j++){
                if(board[i][j]==0)
                    str.append(".");
                else
                    str.append("Q");
            }
            list.add(str.toString());
        }
        result.add(list);
    }

    //Backtracking Solution
    public void solveNQueensUtil (int[][] board , int n , int row,List<List<String>> result){
        //We have placed n queens in nXn board
        if(row==n){
            storeBoardIntoResult(board,n,result);
            return;
        }
        //Check for every column in the current row if a queen can be placed or not
        for(int col = 0 ; col < n ; col++){
            System.out.println(Arrays.deepToString(board)+row+"："+col);
            if(isSafe(board,row,col,n)){
                board[row][col] = 1; //Queen safely placed

                solveNQueensUtil(board , n , row+1,result);
                board[row][col] = 0; //Backtrack by removing the queen
            }
        }
    }

    public boolean isSafe(int[][]  board, int row , int col , int n){
        int i = 0 , j = 0;
        //Check for upper diagonal(left)
        for(i=row-1,j=col-1 ; i>=0 && j>=0 ; i--,j--){
            if(board[i][j]==1) //If queen is found in left upper diagonal
                return false;
        }
        //Check for column from the above
        for(i=0;i<row;i++){
            if(board[i][col]==1) //If queen is found above
                return false;
        }
        //Check for upper diagonal(right)
        for(i=row-1,j=col+1; i>=0 && j<n ; i--,j++){
            if(board[i][j]==1) //If queen is found in right upper diagonal
                return false;
        }
        return true;
    }
}
