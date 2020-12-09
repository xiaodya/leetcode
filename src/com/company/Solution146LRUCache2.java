package com.company;

import java.util.HashMap;

public class Solution146LRUCache2 {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    HashMap<Integer,Node> map;
    int cap=0;
    Node head,tail;

    public Solution146LRUCache2(int capacity){
        map = new HashMap<>();
        cap = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if (this.map.containsKey(key)){
            Node node = this.map.get(key);
            deleteNode(node);
            addHead(node);
            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value){
        if (this.map.containsKey(key)){
            Node node = this.map.get(key);
            node.value = value;
            deleteNode(node);
            addHead(node);
        }else{
            Node node = new Node(key,value);
            this.map.put(key,node);
            addHead(node);
            if (this.map.size() > this.cap){
                this.map.remove(tail.prev.key);
                deleteNode(tail.prev);
            }
        }
    }

    public void addHead(Node node){
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void deleteNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String args[]){
        Solution146LRUCache2 lru = new Solution146LRUCache2(4);
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
