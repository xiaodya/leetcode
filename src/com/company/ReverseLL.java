package com.company;

/**
 * @Author: xiaodya
 * @Date: 2021/2/5 17:07
 * 1.1.1 如何实现一个高效的单向链表逆序输出？
 * 解题： 这是一个反转链表的问题，几次循环可以反转一个链表，并且要输出
 */
class Node{
    int value;
    Node next;
    Node(int value){
        this.value = value;
    }
    Node(int value, Node next){
        this.value = value;
        this.next = next;
    }
}

public class ReverseLL {
    public static Node node1 = new Node(1);
    public static Node node2 = new Node(2,node1);
    public static Node node3 = new Node(3,node2);
    public static Node node4 = new Node(4,node3);
    public static Node node5 = new Node(5,node4);

    //基础写法,反转一个链表。但是要逆序输出得多一次循环，想想一遍循环怎么搞定，通过递归层层递进函数出栈打印刚好是逆序
    public void reverse(Node node){
        Node curNode = node;
        Node prev = null;
        Node temp = null;
        while (null != curNode){
            temp = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = temp;
        }
        while (null != prev){
            System.out.print(prev.value);
            prev = prev.next;
        }
    }

    //一遍解决
    public Node recursion(Node node,Node prev){
        if (null == node.next){
            node.next = prev;
            return node;
        }
        Node temp = node.next;
        node.next = prev;
        prev = node;
        Node tail = recursion(temp,prev);
        System.out.print(temp.value);
        if (prev.next == null) System.out.print(prev.value);
        return tail;
    }

    public static void main(String args[]){
        ReverseLL reverseLL = new ReverseLL();
        reverseLL.reverse(node5);
        System.out.println();
        reverseLL.recursion(node1,null);
        System.out.println();
    }
}
