package com.kk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kingdee
 * @description 回溯算法
 * @date: 2021-11-09 13:36
 */
public class SearchGoalArray {
    /**
     * 最小差值
     */
    private static int dValue = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int goal = 39;
        long startTime = System.currentTimeMillis();
        List<List<Integer>> resultList = new ArrayList<>();
        subsetsWithDupHelper(resultList,nums,goal,0,new LinkedList<Integer>());
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("耗时：" + time);
        System.out.println("数组为："+ Arrays.toString(nums) +"，目标值：" + goal + ",组合数位："+ resultList.size() + ",最接近组合：" +  resultList);
    }

    private static void subsetsWithDupHelper(List<List<Integer>> resultList, int[] nums, int goal, int startIndex, LinkedList<Integer> goalList){
        //目标数组求和
        int sumGoalInt = sumGoalList(goalList);

        if(dValue == Math.abs(sumGoalInt - goal) && goalList.size() > 0 && resultList.size() > 0) {
            LinkedList<Integer> clone = (LinkedList<Integer>)goalList.clone();
            resultList.add(clone);
        }

        //更小的差值就把数组添加
        if(dValue > Math.abs(sumGoalInt - goal) && goalList.size() != 0) {
            dValue = Math.abs(sumGoalInt - goal);
            resultList.clear();
            LinkedList<Integer> clone = (LinkedList<Integer>)goalList.clone();
            resultList.add(clone);
        }

        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){

            /**
             * 我们什么时候应该跳过一个数字？
             * 不仅与前一个号码相同而且当它的前一个号码尚未添加时！
             * i> startIndex表示没有将nums[i-1]添加到路径中
             * 所以如果nums==nums[i-1]那么我们不应该添加nums
             */
            if (i > startIndex && nums[i] == nums[i - 1]){
                continue;
            }
            goalList.add(nums[i]);
            subsetsWithDupHelper(resultList,nums, goal,i + 1,goalList);
            goalList.removeLast();
        }
    }

    private static int sumGoalList( LinkedList<Integer> path){
        int sum = 0;
        for(int i = 0; i< path.size() ; i++){
            sum = sum + path.get(i);
        }
        return sum;
    }
}
