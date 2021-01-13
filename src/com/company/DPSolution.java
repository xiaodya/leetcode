package com.company;

public class DPSolution {
    //leetcode 70
    public int climbStairs(int n) {
        // TODO: 2020/4/4 fibnachi f(n) = f(n-1)+f(n-2)
        if (n<=2) return n;
        int temp1 = 1;
        int temp2 = 2;
        int fib = 0;
        for (int i=3;i<=n;i++){
            fib = temp1 + temp2;
            temp1 = temp2;
            temp2 = fib;
        }
        return fib;
    }

    public int climbStairs2(int n) {
        // TODO: 2020/4/4 fibnachi f(n) = f(n-1)+f(n-2)
        if (n==0 || n==1 || n==2) return n;
        int fib[] = new int[n];
        fib[0] = 1;
        fib[1] = 2;
        for (int i=2;i<n;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib[n-1];
    }

    public int fib(int n){
        if (n <= 1) return n;
        int fib[] = new int[n];
        fib[0] = 1;
        fib[1] = 1;
        for (int i=2;i<n;i++){
            fib[i] = fib[i-1]+fib[i-2];
        }
        return fib[n-1];
    }

    public int fib_recusion(int n){
        if (n <= 1) return n;
        return fib_recusion(n-1)+fib_recusion(n-2);
    }
    
    public static void main(String args[]){
        DPSolution dp = new DPSolution();
        System.out.println(dp.climbStairs(9));
        System.out.println(dp.climbStairs2(9));
        System.out.println(dp.fib(10));
        System.out.println(dp.fib_recusion(10));
    }
}
