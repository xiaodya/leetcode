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

    public static void main(String args[]){
        int[] prices = new int[]{2,4,6,1,7,3,8};
        StockSolution stockSolution = new StockSolution();
        int one = stockSolution.maxProfitOne(prices);
        int two = stockSolution.maxProfitTwo(prices);
        int three = stockSolution.maxProfitThree(prices);
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }
}
