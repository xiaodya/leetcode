package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

//leetcode 212
public class WordSearch {
    class TrieNode{
        TrieNode children[];
        String word;

        TrieNode(){
            children = new TrieNode[26];
        }
    }

    public TrieNode genTrieTree(String[] words){
        TrieNode root = new TrieNode();
        for (String word : words){
            TrieNode p = root;
            for (char c : word.toCharArray()){
                if (p.children[c-'a'] == null){
                    p.children[c-'a'] = new TrieNode();
                }
                p = p.children[c-'a'];
            }
            p.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode node = genTrieTree(words);
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                dfs(board,i,j,node,res);
            }
        }
        return res;
    }

    public void dfs(char[][] board,int i,int j,TrieNode node, List<String> res){
        char c = board[i][j];
        if (c == '#' || node.children[c-'a'] ==  null) return;
        node = node.children[c-'a'];
        if (node.word != null){
            res.add(node.word);
            node.word = null;
        }
        board[i][j] = '#';
        if (i>0) dfs(board,i-1,j,node,res);
        if (j>0) dfs(board,i,j-1,node,res);
        if (i<board.length-1) dfs(board,i+1,j,node,res);
        if (j<board[0].length-1) dfs(board,i,j+1,node,res);
        board[i][j] = c;
    }

    public static void main(String args[]){
        WordSearch ws = new WordSearch();
        char board[][] = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String words[] = {"oath","pea","eat","rain"};
        List res = ws.findWords(board,words);
        System.out.println(res.toString());
    }
}
