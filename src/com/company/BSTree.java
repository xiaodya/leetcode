package com.company;


import java.util.Stack;

public class BSTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args){

    }
    //leetcode 98
    //min max 递归
    public boolean isValidBST(TreeNode root) {
        return checkNode(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean checkNode(TreeNode node, long min, long max) {
        if (node == null){
            return true;
        }
        if(node.val > min && node.val < max){
            return (checkNode(node.left, min, node.val) && checkNode(node.right, node.val, max));
        } else {
            return false;
        }
    }
    //中序遍历 递归
    int pre = Integer.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST2(root.left);
        boolean mid = pre >= root.val ? false : true;
        pre = root.val;
        boolean right = isValidBST2(root.right);
        return left && mid && right;
    }

    //中序遍历 stack
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
    //leetcode 235
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null){
            if (p.val > root.val && q.val > root.val){
                root = root.right;
            }else if (p.val < root.val && q.val < root.val){
                root = root.left;
            }else {
                return root;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestorBSTRecursion(TreeNode root,TreeNode p,TreeNode q){
        if (root == null) return null;
        if (p.val > root.val && q.val > root.val)
            lowestCommonAncestorBSTRecursion(root.right,p,q);
        if (p.val < root.val && q.val < root.val)
            lowestCommonAncestorBSTRecursion(root.left,p,q);
        return root;
    }
    //leetcode 236 最近公共祖先问题
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        return left == null ? right : right == null ? left : root;
    }
}
