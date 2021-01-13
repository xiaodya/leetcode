package com.company;

import java.math.BigInteger;
import java.util.Arrays;

public class BitsSolution {
    //leetcode 191
    public int hammingWeight(int n) {
        int res = 0;
        for (;n!=0;n&=(n-1)){
            res++;
        }
        return res;
    }

    public int hammingWeight2(int n){
        int res = 0;
        for (int i=0;i<32;i++){
            res += n&1;
            n = n >> 1;
        }
        return res;
    }

    public int hammingWeight3(int n){
        int res = 0;
        int mask = 1;
        for (char i : Integer.toBinaryString(n).toCharArray()){
            if (i == '1')
                res++;
        }
        return res;
    }

    //leetcode 231
    public boolean isPowerOfTwo(int n) {
        return n>0 && (n&(n-1))==0;
    }

    //leetcode 331
    public int[] countBits(int num) {
        int bits[] = new int[num+1];
        for (int i=1;i<=num;i++){
            bits[i] = bits[i&(i-1)]+1;
        }
        return bits;
    }

    public int[] countBits2(int num) {
        int[] arr = new int[num+1];
        for(int i=0;i<=num;i++){
            arr[i] = arr[i/2] + (i & 1) ;
        }
        return arr;
    }

    public int[] countBits3(int num){
        int[] dp = new int[num+1];
        int power = 0;
        for(int i=1; i<=num; i++){
            if( (i&(i-1)) == 0) // finding if number is power of 2
                power = i;
            dp[i] = dp[i-power]+1; // adding 1 because the most significant bit increases
        }
        return dp;
    }

    public static void main(String args[]){
        BitsSolution bit = new BitsSolution();
        int res = bit.hammingWeight(7);
        System.out.println(res);
        int res2 = bit.hammingWeight2(7);
        System.out.println(res2);
        int res3 = bit.hammingWeight3(7);
        System.out.println(res3);

        BigInteger b = new BigInteger("10");
        System.out.println(b.toString(2));
        System.out.println(Long.valueOf("11111111111111111111111111111101",2));
        System.out.println(-3 & 0xFF);

        System.out.println(Arrays.toString(bit.countBits(100)));
        System.out.println(Arrays.toString(bit.countBits2(100)));

        System.out.println(100000 % 3);
        System.out.println(3 & 100000);
    }
}
