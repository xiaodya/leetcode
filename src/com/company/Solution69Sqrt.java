package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Solution69Sqrt {
    public double mySqrt(int x) {
        double  r=x,l = 0, mid = 0,y = 0;
        while (Math.abs(r-l) > 1e-10){
            mid = (l+r)/2;
            y = mid*mid;
            if (y > x){
                r = mid;
            }else if (y < x){
                l = mid;
            }else{
                return mid;
            }
        }
        return r;
    }
    //牛顿迭代法
    public double mySqrt2(int x) {
        double r=x;
        while ((r*r - x) > 1e-10){
            r = (r + x/r)/2;
        }
        return r;
    }

    public static void main(String args[]){
        Solution69Sqrt sqrt = new Solution69Sqrt();
        double d1 = sqrt.mySqrt(2);
        double d2 = sqrt.mySqrt2(2);
        BigDecimal b1  =new BigDecimal(d1);
        double f1  =  b1.setScale(10,  RoundingMode.HALF_UP).doubleValue();
        System.out.println(f1);
        BigDecimal b2  =new BigDecimal(d2);
        double f2  =  b2.setScale(10,  RoundingMode.HALF_UP).doubleValue();
        System.out.println(f2);
        DecimalFormat df  =new DecimalFormat("#.000000");
        System.out.println(df.format(d1));
        System.out.println(df.format(d2));
    }
}
