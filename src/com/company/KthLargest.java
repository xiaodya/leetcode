package com.company;

import java.util.PriorityQueue;

public class KthLargest {
    //leetcode 703
    private PriorityQueue<Integer> q;
    private int k;

    public KthLargest(int k, int [] nums){
        this.k = k;
        this.q = new PriorityQueue<>(k);
        for (int n : nums){
            add(n);
        }
    }

    public int add(int val){

        if(q.size() < k){
            q.offer(val);
        }else if(q.peek() < val){
            q.poll();
            q.offer(val);
        }
        return q.peek();
    }
}
