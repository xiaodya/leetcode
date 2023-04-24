package com.company;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: xiaodya
 * @Date: 2021/1/29 17:02
 * 6.1.4 一棵二叉树，求最大通路长度。（即最大左右子树高度之和）
 * 遍历树的问题，理解前序，中序，后序遍历
 * 中序遍历就是dfs，还有一个概念叫做层序遍历，就是bfs
 * 遍历找到左右子树最后的叶子节点即可
 */
public class DeepLengthWithTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    TreeNode treeNode1 = new TreeNode(1);
    TreeNode treeNode2 = new TreeNode(2);
    TreeNode treeNode3 = new TreeNode(3);
    TreeNode treeNode4 = new TreeNode(4);
    TreeNode treeNode5 = new TreeNode(5);
    TreeNode treeNode6 = new TreeNode(6);
    TreeNode treeNode7 = new TreeNode(7);
    TreeNode treeNode8 = new TreeNode(8);
    TreeNode treeNode9 = new TreeNode(9);
    TreeNode treeNode10 = new TreeNode(10);
    TreeNode treeNode11 = new TreeNode(11);
    TreeNode treeNode12 = new TreeNode(12);
    TreeNode treeNode13 = new TreeNode(13);
    {
        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
//        treeNode2.right = treeNode4;
//        treeNode3.left = treeNode5;
//        treeNode4.left = treeNode6;
//        treeNode6.right = treeNode7;
//        treeNode7.right = treeNode8;
//        treeNode8.left = treeNode9;
//        treeNode9.left = treeNode10;
//        treeNode5.right = treeNode11;
//        treeNode5.left = treeNode12;
//        treeNode12.left = treeNode13;
    }
    //递归，分治
    public int minDepth(TreeNode treeNode){
        if (treeNode == null) return 0;
        int left = minDepth(treeNode.left);
        int right = minDepth(treeNode.right);
        return (left == 0 || right == 0) ? left+right+1 : Math.min(left,right)+1;
    }

    //递归，分治
    public int maxDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        return 1 + Math.max(maxDepth(treeNode.left), maxDepth(treeNode.right));
    }

    public int solution_bfs(TreeNode treeNode) {
        if (treeNode == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(treeNode);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            TreeNode treeNodeInner = null;
            for (int i=size;i>0;i--){
                treeNodeInner = queue.poll();
                if (treeNodeInner.left != null){
                    queue.offer(treeNodeInner.left);
                }
                if (treeNodeInner.right != null){
                    queue.offer(treeNodeInner.right);
                }
            }
        }
        return depth;
    }

    public int solution_dfs(TreeNode root){
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int depth = 0;
        int maxdepth = 0;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                depth++;
                if (depth>=maxdepth){
                    maxdepth = depth;
                }
                root =  root.left;
            }
            root = stack.pop();
            root = root.right;
            if (root == null) depth--;
        }
        return maxdepth;
    }

    public static void main(String args[]) {
        DeepLengthWithTree deepLengthWithTree = new DeepLengthWithTree();
        System.out.println("Min:"+deepLengthWithTree.minDepth(deepLengthWithTree.treeNode1));
//        System.out.println("MinLeft:"+deepLengthWithTree.minDepth(deepLengthWithTree.treeNode1.left));
//        System.out.println("MinRight:"+deepLengthWithTree.minDepth(deepLengthWithTree.treeNode1.right));
        System.out.println("Max:"+deepLengthWithTree.maxDepth(deepLengthWithTree.treeNode1));
//        System.out.println("MaxLeft:"+deepLengthWithTree.maxDepth(deepLengthWithTree.treeNode1.left));
//        System.out.println("MaxRight:"+deepLengthWithTree.maxDepth(deepLengthWithTree.treeNode1.right));
//
//        System.out.println("BFSMax:"+deepLengthWithTree.solution_bfs(deepLengthWithTree.treeNode1));
//        System.out.println("BFSMax.Left:"+deepLengthWithTree.solution_bfs(deepLengthWithTree.treeNode1.left));
//        System.out.println("BFSMax.Right:"+deepLengthWithTree.solution_bfs(deepLengthWithTree.treeNode1.right));
//
//        System.out.println("DFSMax:"+deepLengthWithTree.solution_dfs(deepLengthWithTree.treeNode1));
//        System.out.println("DFSMax.Left:"+deepLengthWithTree.solution_dfs(deepLengthWithTree.treeNode1.left));
//        System.out.println("DFSMax.Right:"+deepLengthWithTree.solution_dfs(deepLengthWithTree.treeNode1.right));
//
//        int maxleft = deepLengthWithTree.maxDepth(deepLengthWithTree.treeNode1.left);
//        int maxright = deepLengthWithTree.maxDepth(deepLengthWithTree.treeNode1.right);
//        System.out.println("path="+(maxleft+maxright+1));

    }
}
