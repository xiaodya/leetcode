package com.company;

/**
 * @Author: xiaodya
 * @Date: 2021/1/13 18:24
 */

import java.util.*;

/**
 * //评测题目:
 * // 已知一个任务由多个子任务组成，子任务和子任务之间存在先后完成顺序，
 * // 简单起见，现假设数据结构HashMap<String, List<String>> taskGroup（或者所熟悉语言类似的数据结构）保存了子任务之间的相互关系，
 * // 如：
 * // {
 * //     "A": ["B"],
 * //          "B": ["C"]
 * // }
 * // 表示任务B开始需要子任务A先完成，子任务C开始需要子任务B先完成。
 * // 请用代码实现，给定一组子任务的关系taskGroup时（即不需要考虑taskGroup的初始化），计算所有子任务可能的完成顺序（返回一种有效即可）
 * //
 * // 如使用上述例子作为输入时，相应函数返回结果应为ABC。s
 */
public class SubMissionProblem {
    public int dfscount = 0;
    public int dpcount = 0;

    class Node {
        String key;
        String value;
        Node prev;
        Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<String, Node> map;
    Node head, tail;

    public SubMissionProblem() {
        map = new HashMap<>();
        head = new Node("", "");
        tail = new Node("", "");
        head.next = tail;
        tail.prev = head;
    }

    public void addTail(Node node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    public void addHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void solution(HashMap<String, List<String>> subMission) {
        if (subMission.size() > 0) {
            Map.Entry<String, List<String>> first = subMission.entrySet().iterator().next();
            Node nodeH = new Node(first.getKey(), first.getKey());
            addHead(nodeH);
            solution_head(subMission, first.getKey());
            solution_tail(subMission, first.getKey());
        }
        Node node = head;
        System.out.print("find one way solution:");
        while (null != node.next) {
            System.out.print(node.key);
            node = node.next;
        }
        System.out.println();
    }

    public void solution_head(HashMap<String, List<String>> subMission, String head) {
        for (Map.Entry<String, List<String>> inner : subMission.entrySet()) {
            for (String sub : inner.getValue()) {
                if (sub.equals(head)) {
                    //找到第一个前序任务，继续，但是本层要终止出去
                    Node nodeH = new Node(inner.getKey(), inner.getKey());
                    addHead(nodeH);
                    solution_head(subMission, inner.getKey());
                    return;
                } else {
                    //没有前序了，结束
                }
            }
        }
    }

    public void solution_tail(HashMap<String, List<String>> subMission, String tail) {
        if (subMission.containsKey(tail)) {
            //找到后续，继续
            List<String> sub = subMission.get(tail);
            Node nodeT = new Node(sub.get(0), sub.get(0));
            addTail(nodeT);
            solution_tail(subMission, sub.get(0));
        } else {

        }
    }

    public void solution_sort(HashMap<String, List<String>> subMission) {
        //如果任务有大小关系，O(1)*logN的复杂度
        List<String> list = new ArrayList<String>(subMission.keySet());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 返回相反的compare
                return o2.compareTo(o1);
            }
        });
        System.out.println();
        System.out.println(list.toString());
    }

    public void solution_dfs(HashMap<String, List<String>> subMission) {
        if (subMission.size() > 0) {
            //存下来已经搜索过的前序节点
            HashSet<String> filter = new HashSet<String>();
            HashMap<String, List<String>> result = new HashMap<String, List<String>>();
            for (Map.Entry<String, List<String>> outer : subMission.entrySet()) {
                if (!filter.contains(outer.getKey())) {
                    dfs(subMission, result, filter, outer.getKey(), outer.getKey(), outer.getValue(), 0);
                }
            }
//            System.out.println(filter.toString());
            System.out.println("find all way solution by dfs:" + result.toString());
            System.out.println("find all way solution by dfs count:" + dfscount);
        }
    }

    public String dfs(HashMap<String, List<String>> subMission, HashMap<String, List<String>> result,
                      HashSet<String> filter, String baba, String head, List<String> subList, int k) {
        dfscount++;
        //terminator
        if (k >= subList.size()) return head;
        //process
        String sub = subList.get(k);
        filter.add(sub);
        if (result.containsKey(sub)) {
            result.remove(sub);
        }
        //drill down
        if (subMission.containsKey(sub)) {
            List<String> nextSubList = subMission.get(sub);
            dfs(subMission, result, filter, baba, head + sub, nextSubList, 0);
        } else {
            if (result.containsKey(baba)) {
                List<String> ori = result.get(baba);
                ori.add(head + sub);
                result.put(baba, ori);
            } else {
                List<String> l = new ArrayList<String>();
                l.add(head + sub);
                result.put(baba, l);
            }
        }
        dfs(subMission, result, filter, baba, head, subList, k + 1);
        //reverse status
        return head + sub;
    }

    public void solution_dp(HashMap<String, List<String>> subMission) {
        if (subMission.size() > 0) {
            //存下来已经搜索过的前序节点
            //dp 层层递推，每一层记录当前最优子结构的解，遇到前序节点直接使用，减少重复计算，缺点是多用内存。
            HashSet<String> filter = new HashSet<String>();
            HashMap<String, List<String>> invited = new HashMap<String, List<String>>();
            HashMap<String, List<String>> result = new HashMap<String, List<String>>();
            for (Map.Entry<String, List<String>> outer : subMission.entrySet()) {
                String head = outer.getKey();
                List<String> headSubList = outer.getValue();
                List<String> noinvited = new ArrayList<>();
                List<String> newHeadList = new ArrayList<>();
                for (String sub: headSubList){
                    filter.add(sub);
                    if (result.containsKey(sub)){
                        result.remove(sub);
                    }
                    if (invited.containsKey(sub)){
                        List<String> subList = invited.get(sub);
                        for (String subStr: subList){
                            newHeadList.add(head+subStr);
                        }
                    } else {
                        noinvited.add(sub);
                    }
                }
                invited.put(head,newHeadList);
                if (!filter.contains(head)){
                    result.put(head,newHeadList);
                }
                if (noinvited.size() > 0){
                    dp(subMission, filter, result, invited, head, head, noinvited, 0);
                }
            }
//            System.out.println(filter.toString());
//            System.out.println(invited.toString());
            System.out.println("find all way solution by dp:" + result.toString());
            System.out.println("find all way solution by dp count:" + dpcount);
        }
    }

    public String dp(HashMap<String, List<String>> subMission, HashSet<String> filter, HashMap<String, List<String>> result,
                     HashMap<String, List<String>> invited, String baba, String head, List<String> subList, int k) {
        dpcount++;
        //terminator
        if (k >= subList.size()) return head;
        //process
        String sub = subList.get(k);
        filter.add(sub);
        if (result.containsKey(sub)) {
            result.remove(sub);
        }
        //drill down
        if (subMission.containsKey(sub)) {
            List<String> nextSubList = subMission.get(sub);
            dp(subMission, filter, result, invited, baba, head + sub, nextSubList, 0);
        } else {
            if (invited.containsKey(baba)) {
                List<String> ori = invited.get(baba);
                ori.add(head + sub);
                invited.put(baba, ori);
                if (!filter.contains(baba)){
                    result.put(baba, ori);
                }
            } else {
                List<String> l = new ArrayList<String>();
                l.add(head + sub);
                invited.put(baba, l);
                if (!filter.contains(baba)){
                    result.put(baba, l);
                }
            }
        }
        dp(subMission, filter, result, invited, baba, head, subList, k + 1);

        //reverse status

        return head + sub;
    }

    public static void main(String args[]) {
        SubMissionProblem subMissionProblem = new SubMissionProblem();
        HashMap<String, List<String>> subMission = new HashMap<>();
        subMission.put("A", new ArrayList<String>(Arrays.asList("B", "C", "D", "G")));
        subMission.put("B", new ArrayList<String>(Arrays.asList("D", "E", "F")));
        subMission.put("C", new ArrayList<String>(Arrays.asList("H")));
        subMission.put("E", new ArrayList<String>(Arrays.asList("I")));
        subMission.put("X", new ArrayList<String>(Arrays.asList("A")));
        subMission.put("Z", new ArrayList<String>(Arrays.asList("X")));
        subMission.put("D", new ArrayList<String>(Arrays.asList("C")));
        subMission.put("Y", new ArrayList<String>(Arrays.asList("A")));
        subMission.put("W", new ArrayList<String>(Arrays.asList("Y","B")));
        subMissionProblem.solution(subMission);
        subMissionProblem.solution_dfs(subMission);
        subMissionProblem.solution_dp(subMission);

    }
}
