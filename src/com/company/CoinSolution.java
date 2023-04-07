package com.company;

import java.util.Arrays;

/**
 * @Author: xiaodya
 * @Date: 2023/4/4 1:01 上午
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 */
public class CoinSolution {

    //TODO 状态定义 dp[i,j],前i种硬币在金额总数为j的时候最少使用的硬币个数
    //01背包问题，dp[i,j] = min{dp[i-1,j],dp[i-1][j-coins[i]]+1}
    //完全背包问题，dp[i,j] = min{dp[i-1,j],dp[i][j-coins[i]]+1}

    //一维DP，2层循环
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int dp[] = new int[amount+1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i=0; i<coins.length; i++) {
            for (int j=coins[i]; j<=amount; j++) {
                if (dp[j-coins[i]] != max) {
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    public static void main(String args[]) {
        int coins[] = new int[]{1,2,5};
        int amount = 11;
        CoinSolution coinSolution = new CoinSolution();
        System.out.println(coinSolution.coinChange(coins,amount));
    }

}
