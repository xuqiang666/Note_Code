package com.kk;

import java.util.*;

/**
 * created time: 2021/11/4
 * author: kingdee
 * 给定升序数组，以及目标值，查找出最接近目标值的数组组合
 */
public class FindClosestNumCombination_兰剑 {

    public static void main(String[] args) {
//        int[] arr = {1, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9, 12, 17}; //14
        int[] arr = {3, 6, 7, 15, 24}; //29
//        int[] arr = {2,30,42,67}; //50
//        int[] arr = {3,4,8}; //9
        Object o = ClosestNumCombination(arr, 29);
        System.out.println("----------------------------");
        System.out.println(o);
        System.out.println("----------------------------");

    }

    /**
     * 记录最接近目标值
     */
    private static int min = Integer.MAX_VALUE;
    /**
     * 记录每次循环深度对应的值
     */
    private static final Map<Integer, Integer> depthMap = new HashMap<>();

    /**
     * @param arr    指定数组
     * @param target 目标值
     * @return 所有符合目标值的结合
     */
    public static Deque<List<Integer>> ClosestNumCombination(int[] arr, int target) {
        Deque<List<Integer>> allArrStack = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        /* 1、边界情况 */
        if (arr == null) {
            return allArrStack;
        }
        if (arr.length <= 1 || arr[0] >= target) {
            list.add(arr[0]);
            allArrStack.push(list);
            return allArrStack;
        }
        /* 2、正常情况 */
        // 二分法找出第一个大于|等于 target 的数值以及位置   1,2,4,5,6   3
        if (arr[arr.length - 1] < target) {
            min = target - arr[arr.length - 1];
            list.add(arr[arr.length - 1]);
            allArrStack.push(list);
            FindClosestNum(arr, arr.length - 1, target, new ArrayList<>(), allArrStack);
        } else {
            int leftIndex = 0;
            int rightIndex = arr.length - 1;
            while (leftIndex < rightIndex) {
                int midIndex = (leftIndex + rightIndex) >> 1;
                if (arr[midIndex] > target) {
                    rightIndex = midIndex - 1;
                } else if (arr[midIndex] < target) {
                    leftIndex = midIndex + 1;
                } else {
                    rightIndex = midIndex;
                }
            }
            // 找出了第1个大于等于目标值target的位置 leftIndex
            min = arr[leftIndex] - target;
            list.add(arr[leftIndex]);
            allArrStack.push(list);
            FindClosestNum(arr, leftIndex - 1, target, new ArrayList<>(), allArrStack);
        }

        return allArrStack;
    }

    /**
     * 查找最接近目标值的方法
     *
     * @param arr         指定数组
     * @param rightIndex  数字长度
     * @param target      目标值
     * @param list        集合：用来存放一次拿到的最接近目标值的集合
     * @param allArrQueue 用来存放所有最接近目标值的队列
     */
    private static void FindClosestNum(int[] arr, int rightIndex, int target, List<Integer> list, Deque<List<Integer>> allArrQueue) {

        for (int i = rightIndex; i >= 0; i--) {
            // 过滤一层有重复值的数据
            if (rightIndex > i && arr[i] == arr[i + 1]) continue;
            // 以最大值的第一个开始
            int bigHead = arr[i];
            int depth = 1;
            // 记录第1层
            depthMap.put(depth, bigHead);
            if (target - bigHead < min) {
                while (allArrQueue.pollLast() != null) {
                }
                list.add(bigHead);
                allArrQueue.push(list);
                list = new ArrayList<>();
                min = target - bigHead;
            } else if (target - bigHead == min) {
                list.add(bigHead);
                while (allArrQueue.pollLast() != null) {
                }
                allArrQueue.push(list);
                list = new ArrayList<>();
            }
            if (i >= 1) {
                add(arr, i - 1, target - bigHead, list, allArrQueue, depth);
            }
            list = new ArrayList<>();
            depthMap.clear();
        }
    }

    /**
     * @param arr         指定数组
     * @param index       下标
     * @param target      目标值
     * @param list        用来存储一次最接近目标值的集合
     * @param allArrQueue 用来存储所有最接近目标值的队列
     * @param depth       循环的深度
     */
    private static void add(int[] arr, int index, int target, List<Integer> list, Deque<List<Integer>> allArrQueue, int depth) {
        for (int i = index; i >= 0; i--) {
            // 数值比大的不参与
            if (Math.abs(target - arr[i]) > min && target < arr[i]) continue;
            if (Math.abs(target - arr[i]) == min) {
                // 过滤最后一层有重复值的数据
                if (index > i && arr[i] == arr[i + 1]) continue;
                // 从每一层去拿对应数据
                for (Map.Entry<Integer, Integer> entry : depthMap.entrySet()) {
                    list.add(entry.getValue());
                }
                list.add(arr[i]);
                allArrQueue.push(list);
                list = new ArrayList<>();
                continue;
            }
            if (Math.abs(target - arr[i]) < min) {
                // 更新最小值
                min = Math.abs(target - arr[i]);
                // 从每一层去拿对应数据
                for (Map.Entry<Integer, Integer> entry : depthMap.entrySet()) {
                    list.add(entry.getValue());
                }
                list.add(arr[i]);
                // 说明有新的更接近目标的值存在，需要先把历史存的清除
                while (allArrQueue.pollLast() != null) {
                }
                allArrQueue.push(list);
                list = new ArrayList<>();
                if (target < arr[i]) {
                    continue;
                }

            }
            if (i >= 1) {
                // 过滤循环深层中有重复的数据
                if (index > i && arr[i] == arr[i + 1]) continue;
                depthMap.put(++depth, arr[i]);
                add(arr, i - 1, target - arr[i], list, allArrQueue, depth);
                list = new ArrayList<>();
                depthMap.remove(depth--);
            }
        }

    }

}
