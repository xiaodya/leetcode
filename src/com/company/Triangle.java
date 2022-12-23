package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//leetcode 120 三角形最小路径和
public class Triangle {
    int count3 = 0;

    public int minimumTotal(List<List<Integer>> triangle) {
        int count1 = 0;
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) // TODO:自上向下推导，每层头和尾节点没有2条分支
                    triangle.get(i).set(j, triangle.get(i - 1).get(0) + triangle.get(i).get(j));
                else if (j == triangle.get(i).size() - 1)
                    triangle.get(i).set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                else
                    triangle.get(i).set(j, Math.min(triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j), triangle.get(i - 1).get(j) + triangle.get(i).get(j)));
                count1++;
            }
        }
        System.out.println("count1=" + count1);
        System.out.println(triangle);
        return triangle.get(triangle.size() - 1).stream().mapToInt(v -> v).min().orElse(0);
    }

    public int minimumTotalDp(List<List<Integer>> triangle) {
        // TODO: 2020/4/5 状态定义，转化DP方程，自底向上递归+中间结果（最优子结构）
        int count2 = 0;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = triangle.get(i).size()-1; j >= 0 ; j--) { // TODO:此处可以从前往后，也可以从后往前
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
                count2++;
            }
        }
        System.out.println("count2="+count2);
        return triangle.get(0).get(0);
    }

    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int ret = dfs(triangle, 0, 0);
        System.out.println("count3=" + count3);
        return ret;
    }


    public int dfs(List<List<Integer>> triangle, int i, int j) {
        count3++;
        if (i + 1 >= triangle.size()) return triangle.get(i).get(j);
        return triangle.get(i).get(j) + Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1));
    }

    //Top down
    public int minimumTotalTopDown(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        Integer[][] cache = new Integer[triangle.size()][triangle.size()];
        return dfs_topdown(0, 0, triangle, cache);
    }


    int dfs_topdown(int row, int pos, List<List<Integer>> triangle, Integer[][] cache) {
        //Out of bounds so just return sum previous value must be leaf node
        if (row + 1 >= triangle.size()) {
            return triangle.get(row).get(pos);
        }
        if (cache[row][pos] != null) return cache[row][pos];

        cache[row][pos] = triangle.get(row).get(pos) + Math.min(dfs_topdown(row + 1, pos, triangle, cache), dfs_topdown(row + 1, pos + 1, triangle, cache));

        return cache[row][pos];

    }


    //Bottom up
    public int minimumTotalBottomUp(List<List<Integer>> triangle) {

        int[][] dp = new int[triangle.size()][triangle.size()];
        //Remember base case is just returning leaf nodes
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }

        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int pos = 0; pos < triangle.get(row).size(); pos++) {
                dp[row][pos] = triangle.get(row).get(pos) + Math.min(dp[row + 1][pos + 1], dp[row + 1][pos]);
            }
        }

        return dp[0][0];
    }

    //Bottom up O(n) space complexity.
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {

            int[] dp = new int[triangle.size()];
            int[] dp1 = new int[triangle.size()];
            //Remember base case is just returning leaf nodes
            for (int i = 0; i < triangle.size(); i++) {
                dp[i] = triangle.get(triangle.size() - 1).get(i);
            }

            for (int row = triangle.size() - 2; row >= 0; row--) {
                for (int pos = 0; pos < triangle.get(row).size(); pos++) {
                    dp1[pos] = triangle.get(row).get(pos) + Math.min(dp[pos + 1], dp[pos]);
                }
                dp = dp1;
            }

            return dp[0];
        }
    }


    public static void main(String args[]) {
        Triangle triangle = new Triangle();
//        int[][] a = new int[][]{{2},{3,100},{6,5,7},{1000,10000,8,3}};
//        List<List<Integer>> l = new ArrayList<>();
//        for (int i=0;i<a.length;i++){
//            l.add(Arrays.stream(a[i]).boxed().collect(Collectors.toList()));
//        }
        List<List<Integer>> l = Arrays.asList(
                Arrays.asList(    2),
                Arrays.asList(  3, 4),
                Arrays.asList( 5, 6, 7),
                Arrays.asList(7, 8, 9, 10)
        );
//        System.out.println(triangle.minimumTotal(l));
//        System.out.println(triangle.minimumTotalDp(l));
        System.out.println(triangle.minimumTotalRecursion(l));
    }
}
