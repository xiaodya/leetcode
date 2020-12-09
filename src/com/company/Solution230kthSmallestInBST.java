package com.company;

import java.util.Stack;

public class Solution230kthSmallestInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int res = 0,k = 0;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        this.k = k;
        dfs(root);
        return res;

    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        k--;
        if (k==0) this.res = root.val;
        dfs(root.right);
    }

    public int kthSmallest2(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) return root.val;
            root = root.right;
        }
        return 0;
    }


    public static void main(String args[]){

    }
}
