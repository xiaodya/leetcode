package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.stream.Collectors;

public class TwoSum {
    //leetcode 1
    public static void main(String args[]){
        int [] nums = new int[]{2,7,5,11};
        int target = 9;
        int [] re = twosum(nums,target);
        int [] re2 = twosum_map(nums,target);
        System.out.println(Arrays.toString(re));
        System.out.println(Arrays.toString(re2));

        int [] threeNums = new int[]{0,2,2,3,0,1,2,3,-1,-4,2};
//        int [] threeNums = new int[]{-1,0,1,2,-1,-4};
        threeSum(threeNums);
        threeSumHeadTail(threeNums);
        threeSumUsingMap(threeNums);
        threeSumUsingTwoSum(threeNums);
    }

    public static int[] twosum(int[] nums, int target){
        for(int i = 0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twosum_map(int[] nums, int target){
        Map<Integer,Integer> temp = new HashMap();
        for(int i=0;i<nums.length;i++){
            int sub = target - nums[i];
            if(null != temp.get(sub)){
                return new int[] {i,temp.get(sub)};
            }else{
                temp.put(nums[i],i);
            }
        }
        return new int[0];
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        //O(n*n*n)复杂度
        for(int i = 0;i<nums.length-2;i++){
            for(int j=i+1;j<nums.length-1;j++){
                for(int k=j+1;k<nums.length;k++){
                    if(nums[i]+nums[j]+nums[k] == 0){
                        res.add(Arrays.asList(nums[i],nums[j],nums[k]));
                        Integer[] temp = new Integer[]{nums[i],nums[j],nums[k]};
                        Arrays.sort(temp);
                        set.add(Arrays.asList(temp));
                    }
                }
            }
        }
        List<List<Integer>> res2 = new ArrayList<>(set);
        //暴力破解通过set去重
        System.out.println(res2.toString());
        //暴力破解未去重
        System.out.println(res.toString());
        return res;
    }

    public static List<List<Integer>> threeSumHeadTail(int[] nums){
        if (nums == null || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int head = i+1;
            int tail = nums.length-1;
            while(head<tail){
                if(nums[i]+nums[head]+nums[tail]==0){
                    res.add(Arrays.asList(nums[i],nums[head],nums[tail]));
                    while(head < tail && nums[head] == nums[head+1]){
                        head++;
                    }
                    while(head < tail && nums[tail] == nums[tail-1]){
                        tail--;
                    }
                    head++;
                    tail--;
                }else if (nums[i]+nums[head]+nums[tail] > 0){
                    tail--;
                }else if (nums[i]+nums[head]+nums[tail] < 0){
                    head++;
                }
            }
        }
        System.out.println("threeSumHeadTail:"+res.toString());
        return res;
    }

    public static List<List<Integer>> threeSumUsingMap(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
//        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
//            if (i > 0 && nums[i] == nums[i - 1]) continue;
            HashMap<Integer,Integer> temp = new HashMap<>();
            for(int j=i+1;j<nums.length;j++){
                int a = -(nums[i]+nums[j]);
                if (null != temp.get(a)){
                    //map去重
//                    if(temp.get(a) == 0){
//                        res.add(Arrays.asList(nums[i],nums[j],a));
//                        temp.put(a,1);
//                    }
                    //array去重
                    if (!res.contains(Arrays.asList(nums[i],nums[j],a))){
                        res.add(Arrays.asList(nums[i],nums[j],a));
                    }
                }else{
                    temp.put(nums[j],0);
                }
            }
        }
        System.out.println("threeSumUsingMap:"+res.toString());
        return res;
    }

    public static List<List<Integer>> twosum_map_for_threesum(int[] nums, int target){
        if(nums.length < 2) return new ArrayList<>();
        Map<Integer,Integer> temp = new HashMap();
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int sub = target - nums[i];
            if(null != temp.get(sub)){
                if(temp.get(sub) == 0) {
                    //此处不能用Arrays.asList,后面需要调用add方法，Arrays返回的list是内部类，没有重写add方法，需要直接实例化
                    ArrayList<Integer> al = new ArrayList<>();
                    al.add(nums[i]);
                    al.add(sub);
                    res.add(al);
                    temp.put(sub, 1);
                }
            }else{
                temp.put(nums[i],0);
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSumUsingTwoSum(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            HashMap<Integer, Integer> temp = new HashMap<>();
            List<Integer> list1 = Arrays.stream(nums).boxed().collect(Collectors.toList());
//            System.out.println(list1.toString());
//            System.out.println(list1.subList(i+1,nums.length).toString());
//            Integer[] integers = (Integer[]) list1.subList(i+1,nums.length).toArray(new Integer[nums.length-i-1]);
            int[] arr1 = list1.subList(i+1,nums.length).stream().mapToInt(Integer::valueOf).toArray();
//            int[] arr2 = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
            System.out.println(Arrays.toString(arr1));
//            System.out.println(Arrays.toString(arr2));
            List<List<Integer>> re2 = twosum_map_for_threesum(arr1,-nums[i]);
            for (List<Integer> twosum_res : re2) {
                twosum_res.add(Integer.valueOf(nums[i]));
                res.add(twosum_res);
            }
        }
        System.out.println(res.toString());
        return res;
    }
}
