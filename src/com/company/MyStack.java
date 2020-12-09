package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    //leetcode 225
    private Queue<Integer> queue = new LinkedList<>();

    public MyStack(){

    }

    public void push(int x){
        if(!queue.isEmpty()){
            Queue<Integer> temp = new LinkedList<>();
            while(!queue.isEmpty()){
                temp.add(queue.poll());
            }
            queue.add(x);
            while (!temp.isEmpty()){
                queue.add(temp.poll());
            }
        }else{
            queue.add(x);
        }
    }

    public int top(){
        if(!queue.isEmpty())
            return queue.peek();
        return 0;
    }

    public int pop(){
        if(!queue.isEmpty())
            return queue.poll();
        return 0;
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
