package com.company;

//import com.sun.tools.classfile.ConstantPool;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();

        System.out.println("param_2="+param_2+";param_3="+param_3+";param_4="+param_4);
        int[] windows = new int[]{3, 6, 1, 2, -1, -4};
        maxSlidingWindowUsingArray2(windows,3);
        System.out.println(myPowRecursion(2,11)+"===="+myPowLoop(2,7));
        System.out.println(myPowLoop(2,11));
    }

    //leetcode 239
    public static int[] maxSlidingWindowUsingHeap(int[] nums, int k){
        if (nums.length == 0) {
            return new int[0];
        }
        int res[] = new int[nums.length-k+1];
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(k,(i1, i2) -> (Integer.compare(i2, i1)));
        for(int i=0;i<nums.length;i++){
            if (i >= k && pQueue.size() >= k){
                pQueue.remove(nums[i-k]);
            }
            pQueue.add(nums[i]);
            if (i >= k - 1){
                res[i-k+1] = pQueue.peek();
            }
        }
        return res;
    }

    public static int[] maxSlidingWindowUsingDeque(int[] nums, int k){
        if (nums.length == 0) {
            return new int[0];
        }
        int res[] = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<nums.length;i++){
            if (i >= k && deque.peek() <= i-k){
                deque.remove();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.removeLast();
            }
            deque.add(i);
            if(i >= k - 1){
                res[i-k+1] = nums[deque.peek()];
            }
        }
        return res;
    }

    public static int[] maxSlidingWindowUsingArray(int[] nums, int k){
        if (nums.length == 0) {
            return new int[0];
        }

        int[] maxes = new int[nums.length - k + 1];

        int i, j;
        int maxPos = -1;

        for (i = 0; i <= nums.length - k; ++i) {
            // Ending index of the current window
            j = i + k - 1;

            // new element >= max of last window
            // that means new element is max in the two windows
            // here using >= to make maxPos stay in the windows for a longer time
            if (maxPos != -1 && nums[j] >= nums[maxPos]) {
                maxPos = j;
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is also in this window
            // => it means the max of the last window is still the max of this window
            else if (i <= maxPos) {
                maxes[i] = nums[maxPos];
            }
            // new element < max of last window
            // AND the max of last window is not in this window
            // So we do not know which element is the max in this window, we have to scan the window to find it
            else {
                int maxWindow = Integer.MIN_VALUE;
                int maxPosWindow = 0;
                for (int z = i; z <= j; ++z) {
                    if (nums[z] > maxWindow) {
                        maxPosWindow = z;
                        maxWindow = nums[z];
                    }
                }
                maxPos = maxPosWindow;
                maxes[i] = nums[maxPos];
            }
        }
        return maxes;
    }

    public static int[] maxSlidingWindowUsingArray2(int[] nums, int k) {
        if(k == 0 || nums.length == 0) return new int[0]; // special case;
        int MAX = -1;
        int[] ans = new int[nums.length - k + 1];
        //滑动窗口移动，理论值O(n*k)
        for(int left = 0, right = k - 1; right < nums.length; right++, left++)
        {
            //如果超出则将左边推出
            if(MAX < left){
                MAX = left;
                for(int i = left; i <= right; i++){
                    if(nums[MAX] < nums[i]) MAX = i;
                }
            }
            else if (nums[MAX] < nums[right]){
                MAX = right;
            }
            System.out.println(" left:"+left+" right="+right+" Max="+MAX);
            ans[left] = nums[MAX];
        }
        System.out.println(Arrays.toString(ans));
        return ans;

    }

    public static double myPowRecursion(double x,int n){
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n < 0){
            return 1/myPowRecursion(x,-n);
        }
        if ((n&1) == 1) return x * myPowRecursion(x,n-1);
        return myPowRecursion(x*x,n>>1);
//        double pow = myPowRecursion(x,n >> 1);
//        return ((n&1)==1?x:1) * pow * pow;
    }

    public static double myPowLoop(double x,int n){
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n < 0){
            return 1/myPowLoop(x,-n);
        }
        double pow = 1;
        while ( n > 0){

            if ((n & 1) == 1){
                pow *= x;
            }
            /*pow *= x 与 x*=x 的顺序不能换*/
            x *= x;
            n >>= 1;
        }
        return pow;
    }

}
