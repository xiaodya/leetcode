package com.company;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution146LRUCache {
    public Map<Integer,Integer> map;
    public int cap;

    public Solution146LRUCache(int capacity){
        this.map = new LinkedHashMap<Integer, Integer>();
        this.cap = capacity;
    }

    public int get(int key){
        if (!this.map.containsKey(key)){
            return -1;
        }else{
            int value = this.map.get(key);
            this.map.remove(key);
            this.map.put(key,value);
            return value;
        }
    }

    public void put(int key, int value){
        if (this.map.containsKey(key)){
            this.map.remove(key);
        }
        this.map.put(key,value);
        if (this.map.size() > this.cap){
            int remove = this.map.keySet().iterator().next();
            this.map.remove(remove);
        }
    }

    public static void main(String args[]){
        Solution146LRUCache lru = new Solution146LRUCache(4);
        lru.put(1,1);
        lru.put(2,2);
        System.out.println(lru.get(1));
        lru.put(3,3);
        lru.put(4,4);
        System.out.println(lru.get(2));
        lru.put(5,5);
        System.out.println(lru.get(1));

    }
}
