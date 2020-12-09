package com.company;

import java.util.Stack;

public class MyQueue {
    //leetcode 232
    private Stack<Integer> input = new Stack<>();
    private Stack<Integer> output = new Stack<>();

    public MyQueue(){

    }

    public void push(int x){
        input.push(x);
    }

    public int pop(){
        if(output.isEmpty()){
            while (!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    public int peek(){
        if(!output.isEmpty()){
            return output.peek();
        }else{
            while (!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    public boolean empty(){
        return input.isEmpty() && output.isEmpty();
    }
}
