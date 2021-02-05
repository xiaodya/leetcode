package com.company;

/**
 * @Author: xiaodya
 * @Date: 2021/2/5 18:42
 * 1.1.2 已知sqrt(2)约等于1.414，要求不用数学库，求sqrt(2)精确到小数点后10位
 * 分治法
 */
public class Sqrt {

    public double sqrt(int n){
        double big=n, low=0, temp=0, mid=0;
        while (Math.abs(big-low) > 1e-10){
            mid = (big+low)/2;
            temp = mid*mid;
            if (temp > n){
                big = mid;
            } else if(temp < n){
                low = mid;
            } else{
                return mid;
            }
        }
        return big;
    }

    public static void main(String args[]){
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(2));
    }

}
