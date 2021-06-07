package com.company;

/**
 * @Author: xiaodya
 * @Date: 2021/6/4 17:58
 */
public class StockSolution {

    public int maxProfitOne(int[] prices){
        int sell = 0, buy = Integer.MIN_VALUE;
        for (int i=0;i<prices.length;i++){
            buy = Math.max(buy,-prices[i]);         //find min stock
            sell = Math.max(sell,buy+prices[i]);    //max - min = result
        }
        return sell;
    }

    public int maxProfitTwo(int[] prices){
        int sell = 0,sell2 = 0, buy = Integer.MIN_VALUE,buy2 = Integer.MIN_VALUE;
        for (int i=0;i<prices.length;i++){
            buy = Math.max(buy,-prices[i]);         //总是买最便宜的股票
            sell = Math.max(sell,buy+prices[i]);    //总是得到最大的差值
            buy2 = Math.max(buy2,sell - prices[i]); //第二次买的时候手里的钱包含第一笔卖掉赚的钱
            sell2 = Math.max(sell2,buy2+prices[i]); //最后结果是两次买卖最大值
        }
        return sell2;
    }

    public int maxProfitThree(int[] prices){
        int sell = 0,sell2 = 0,sell3=0, buy = Integer.MIN_VALUE,buy2 = Integer.MIN_VALUE,buy3=Integer.MIN_VALUE;
        for (int i=0;i<prices.length;i++){
            buy = Math.max(buy,-prices[i]);
            sell = Math.max(sell,buy+prices[i]);
            buy2 = Math.max(buy2,sell - prices[i]);
            sell2 = Math.max(sell2,buy2+prices[i]);
            buy3 = Math.max(buy3,sell2 - prices[i]);
            sell3 = Math.max(sell3,buy3+prices[i]);
        }
        return sell3;
    }

    /**
     * 1次
     * @param prices
     * @return
     */
    public int maxProfitOneDP(int[] prices){
        int[][] profit = new int[prices.length][3];
        int res = 0;
        for (int i=0;i<prices.length;i++){
            if (i==0){
                profit[i][0] = 0;           //状态位，0没有股票
                profit[i][1] = -prices[i];  //1手里有股票
                profit[i][2] = 0;           //1手里有股票
            }else{
                profit[i][0] = profit[i-1][0];
                profit[i][1] = Math.max(profit[i-1][1],profit[i-1][0]-prices[i]);
                profit[i][2] = profit[i-1][1]+prices[i];
            }
            res = Math.max(res,Math.max(profit[i][0],profit[i][2]));
        }
        return res;
    }

    /**
     * 无限次
     * @param prices
     * @return
     */
    public int maxProfitUnLimitDP(int[] prices){
        int[][] profit = new int[prices.length][2];
        int res = 0;
        for (int i=0;i<prices.length;i++){
            if (i==0){
                profit[i][0] = 0;           //状态位，0没有股票
                profit[i][1] = -prices[i];  //1手里有股票
            }else{
                profit[i][0] = Math.max(profit[i-1][1]+prices[i],profit[i-1][0]);
                profit[i][1] = Math.max(profit[i-1][1],profit[i-1][0]-prices[i]);
            }
            res = Math.max(res,Math.max(profit[i][0],profit[i][1]));
        }
        return res;
    }

    /**
     * 无限次，贪心
     */
    public int maxProfitUnLimitGreedy(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1 ] - prices[i];
            }
        }
        return profit;
    }

    /**
     * 泛型dp
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitK(int k, int[] prices){
        return 0;
    }

    public static void main(String args[]){
        int[] prices = new int[]{2,4,6,1,7,3,8,5,9};
        StockSolution stockSolution = new StockSolution();
        int one = stockSolution.maxProfitOne(prices);
        int two = stockSolution.maxProfitTwo(prices);
        int three = stockSolution.maxProfitThree(prices);
        System.out.println("one="+one);
        System.out.println("two="+two);
        System.out.println("three="+three);

        int oneDp = stockSolution.maxProfitOneDP(prices);
        int unLimitDp = stockSolution.maxProfitUnLimitDP(prices);
        int unLimitGreedy = stockSolution.maxProfitUnLimitGreedy(prices);
        System.out.println("oneDp="+oneDp);
        System.out.println("unLimitDp="+unLimitDp);
        System.out.println("unLimitGreedy="+unLimitGreedy);

    }
}
