package com.company;

//leetcode 208 字典树
class Trie {

    class Node {
        boolean isEnd;
        Node  children[];
        Node (boolean isEnd){
            this.isEnd = isEnd;
            children = new Node[26];
        }
    }

    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node(false);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node node = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (node.children[c-'a'] == null){
                node.children[c-'a'] = new Node(false);
            }
            node = node.children[c-'a'];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (node.children[c-'a'] != null){
                node = node.children[c-'a'];
            }else{
                return false;
            }
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if (node.children[c-'a'] != null){
                node = node.children[c-'a'];
            }else{
                return false;
            }
        }
        return true;
    }
}

