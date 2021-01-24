package com.company;


/**
 每周一题之2021/1/24
 头条面试题：6.1.2 一个环有10个节点，编号0-9。从0点出发，走N步又能回到0点，共有多少种走法？
 切题：理解题意，只能正着走，不能往回走，从0走到0，所以是10个节点，不是9个节点，注意避坑，这些都要和面试官沟通，让面试官看到你的解题思路。
 先暴力破解，dfs
 再想想，这个题目就是dp的经典题型
 状态定义：a[n]=到第n个节点有多少种走法
 dp方程：a[n]=a[1]+a[2]+...a[n]+1，最后的结果就是a[n]，从状态方程来看就是2的N次方
 当前节点的走法=之前所有节点之和，因为题目没有限制你每一步能跨越的节点数，所以一步可以跨越任意数的节点，即可以从0一步走到9
 */
public class CycleStep {

    int allcount = 0;

    public void solution_dfs(int start, int end){
        dfs(start,end+1,end-start+1);
        System.out.println("solution_dfs:"+allcount);
    }

    public void dfs(int stepnode,int allnode,int leftnode){
        //terminator
        if (leftnode == 0) {
            allcount++;
            return;
        }
        //process

        //drill down
        int canmove = 1;
        for (int i = 1;i <= allnode;i++,canmove++){
            if ((leftnode - canmove) >= 0){
                dfs(canmove,allnode,leftnode - canmove);
            }
        }
        //reverse status
    }

    public void solution_dp(int start, int end){
        int allnode = end-start+1;
        int[] stepn = new int[allnode];
        stepn[start] = 1;
        for (int i=start+1;i<=allnode-1;i++){
            for (int j=start;j<i;j++){
                stepn[i] += stepn[j];
            }
            stepn[i] += 1;
        }
        System.out.println("solution_db:"+stepn[allnode-1]);
    }

    public static void main(String args[]){
        CycleStep cycleStep = new CycleStep();
        cycleStep.solution_dfs(0,4);
        cycleStep.solution_dp(0,10);
    }
}
