package com.company;

import com.sun.javaws.IconUtil;

//152. Maximum Product Subarray
public class Solution152 {
    //暴力破解
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int res = 0;
        for (int i=0;i<nums.length;i++){
            int temp = nums[i];
            for (int j=i+1;j<nums.length;j++){
                temp *= nums[j];
                res = Math.max(temp,res);
            }
            res = Math.max(res,nums[i]);
        }
        return res;
    }

    public int maxProduct2(int[] nums){
        int curmax = nums[0];
        int curmin = nums[0];
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            int temp = curmax;
            curmax = Math.max(Math.max(curmax*nums[i],curmin*nums[i]),nums[i]);
            curmin = Math.min(Math.min(temp*nums[i],curmin*nums[i]),nums[i]);
            res = Math.max(curmax,curmin);
        }
        return res;
    }

    //recursion
    public int maxProductRecursion(int[] nums) {
        if (nums.length == 0) return 0;

        return dfs(nums,1,"2,",nums[0]);
    }

    private int dfs(int[] nums, int i,String str, int product) {
        //terminator
        if (i >= nums.length) {
            System.out.println(str);
            System.out.println(product);
            return product;
        }
        //process
        str += nums[i]+",";
        product = Math.max(nums[i],Math.max(product,product*nums[i]));
        //drill down
        dfs(nums,i+1,str,product);
        //reverse status
        return product;
    }


    public static void main(String args[]){
        Solution152 solution = new Solution152();
//        System.out.println(solution.maxProduct(new int[]{2,-5,-2,-4,3}));
        System.out.println(solution.maxProductRecursion(new int[]{2,-5,-2,-4,3}));
    }
}
