package com.company;

import java.util.*;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
     ListNode(int x, ListNode next){val=x;this.next=next;}
}

public class ReverseLinkedList {
    public static ListNode node5 = new ListNode(5);
    public static ListNode node4 = new ListNode(4,node5);
    public static ListNode node3 = new ListNode(3,node4);
    public static ListNode node2 = new ListNode(2,node3);
    public static ListNode node1 = new ListNode(1,node2);

    public static void main(String[] args) {
        // write your code here
//        System.out.println(reverse(node1).val);
        System.out.println(recursionReverse(node1,null).val);
    }
    //leetcode 206
    public static ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        ListNode temp = null;
        while (cur != null){
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static ListNode recursionReverse(ListNode head,ListNode prev){
        if (head.next == null) {
            head.next = prev;
            return head;
        }
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
        ListNode tail = recursionReverse(head,prev);
        System.out.println(head.val);
        if (prev.next == null) System.out.println(prev.val);
        return tail;
    }

    //leetcode 24
    public static ListNode swapNode(ListNode head){
        //建立头指针，从而调换头指针的两个后继节点，循环下去，最后输出头指针的指向节点
        ListNode prev = new ListNode(0);
        prev.next = head;
        head = prev;
        ListNode tempOne = null;
        ListNode tempTwo = null;
        while(head.next != null && head.next.next != null){
            tempOne = head.next;
            tempTwo = tempOne.next;
            head.next = tempTwo;
            tempOne.next = tempTwo.next;
            tempTwo.next = tempOne;
            head = tempOne;
        }
        return prev.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev=new ListNode(0);
        prev.next=head;
        int i=1;
        int loop=0;
        ListNode cur=head;
        ListNode subListHead=null;//子链表的头部节点指针
        ListNode subListTile=null;//子链表的尾部节点指针
        ListNode tmp=null;

        while(cur!=null ){

            subListHead=cur;
            subListTile=cur;
            while(i < k &&cur.next!=null){
                cur=cur.next;
                i++;
            }
            loop++;

            tmp= cur.next;
            cur.next=null;
            //将子链表反转过来
            if(i==k)
                subListHead=reverseK(subListHead);
            i=1;
            //将第一个指针指向
            if(loop==1)
                head=subListHead;
            //前后指针重新连接
            prev.next=subListHead;
            prev=subListTile;
            cur=tmp;

        }
        return head;

    }

    public ListNode reverseK(ListNode head){
        ListNode cur=head ;
        ListNode pre=null;
        while(cur !=null){
            ListNode tmpNext=cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmpNext;

        }
        return pre;

    }

    //leetcode 141
    public static boolean hasCycle(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        while(p1 != null && p2 != null && p1.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }

    //leetcode 20
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char temp = s.charAt(i);
            if(temp == '(' || temp == '[' || temp == '{'){
                stack.push(temp);
                continue;
            }
            if(temp == ')' && (stack.isEmpty() || stack.pop() != '(')){
                return false;
            }
            if(temp == ']' && (stack.isEmpty() || stack.pop() != '[')){
                return false;
            }
            if(temp == '}' && (stack.isEmpty() || stack.pop() != '{')){
                return false;
            }
        }
        return stack.isEmpty();
    }
    //leetcode 20
    public static boolean isValidUsingMap(String s){
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for(char c : s.toCharArray()){
            if (!map.containsKey(c)){
                stack.push(c);
                continue;
            }else if (stack.isEmpty() || !map.get(c).equals(stack.pop())){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
