package com.kk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FeedTest {

    @Test
    public void test232() {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            list.add((int) (Math.random() * (39997))+1);
        }
        Integer[] supplyArray = list.toArray(new Integer[0]);

        int target = 29997;
        notAllowedSend(supplyArray, target);
        System.out.println(System.currentTimeMillis() - startTime + "ms");
//        allowedSend(supplyArray, target, supplyArray.length);
//        allowedSend2(supplyArray, target, supplyArray.length);
        System.out.println(list);
    }

    public static void allowedSend2(int[] supplyArray, int target, int length) {
        int maxSumQty = target + supplyArray[0];
        // 只选供应数据中最大的单品所造成的差
        int maxAbs = Integer.MAX_VALUE;
        boolean[][] states = new boolean[length][maxSumQty + 1];
        states[0][0] = true;
        if (supplyArray[0] <= maxSumQty) {
            states[0][supplyArray[0]] = true;
        }
        int i, j;
        for (i = 1; i < length; ++i) {
            for (j = 0; j <= maxSumQty; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            for (j = 0; j <= maxSumQty - supplyArray[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + supplyArray[i]] = true;
                }
            }
        }
        int realQty = 0;
        for (j = target; j <= maxSumQty; ++j) {
            if (states[length - 1][j]) {
                int abs = Math.abs(j - target);
                if (abs <= maxAbs) {
                    realQty = j;
                    maxAbs = abs;
                }
            }
        }
        int res = realQty;
        List<Integer> selectedList = new ArrayList<>();
        for (i = length - 1; i >= 1; --i) {
            if (realQty - supplyArray[i] >= 0 && states[i - 1][realQty - supplyArray[i]]) {
                selectedList.add(supplyArray[i]);
                realQty = realQty - supplyArray[i];
            }
        }
        if (realQty != 0) {
            selectedList.add(supplyArray[0]);
        }
        System.out.println("最优结果：" + res + "所选集合：" + selectedList);
    }


    /**
     * 不允许超发，即解不允许大于目标数据
     */
    public static void notAllowedSend(Integer[] supplyArray, int target) {
        int length = supplyArray.length;
        boolean[][] states = new boolean[length][target + 1];
        states[0][0] = true;
        if (supplyArray[0] <= target) {
            states[0][supplyArray[0]] = true;
        }
        int i, j;
        for (i = 1; i < length; ++i) {
            for (j = 0; j <= target; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            for (j = 0; j <= target - supplyArray[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + supplyArray[i]] = true;
                }
            }
        }
        // 找到最优解，不允许超发可能不存在解
        int solution = 0;
        for (j = target; j >= 0; j--) {
            if (states[length - 1][j]) {
                solution = j;
                break;
            }
        }
        if (solution == 0) {
            System.out.println("无解");
            return;
        }
        List<Integer> result = new ArrayList<>();
        for (i = length - 1; i >= 1; --i) {
            if (solution - supplyArray[i] >= 0 && states[i - 1][solution - supplyArray[i]]) {
                result.add(supplyArray[i]);
                solution = solution - supplyArray[i];
            }
        }
        if (solution != 0) {
            result.add(supplyArray[0]);
        }
        System.out.println("最优结果：" + j + " 所选集合：" + result);
    }


    public static void allowedSend(int[] supplyArray, int target, int length) {
        int maxSumQty = target + supplyArray[0];
        // 只选供应数据中最大的单品所造成的差
        int maxAbs = Integer.MAX_VALUE;
        boolean[][] states = new boolean[length][maxSumQty + 1];
        states[0][0] = true;
        if (supplyArray[0] <= maxSumQty) {
            states[0][supplyArray[0]] = true;
        }
        int i, j;
        for (i = 1; i < length; ++i) {
            for (j = 0; j <= maxSumQty; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            for (j = 0; j <= maxSumQty - supplyArray[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + supplyArray[i]] = true;
                }
            }
        }
        int realQty = 0;
        for (j = supplyArray[supplyArray.length - 1]; j <= maxSumQty; ++j) {
            if (states[length - 1][j]) {
                int abs = Math.abs(j - target);
                if (abs <= maxAbs) {
                    realQty = j;
                    maxAbs = abs;
                }
            }
        }
        int res = realQty;
        List<Integer> selectedList = new ArrayList<>();
        for (i = length - 1; i >= 1; --i) {
            if (realQty - supplyArray[i] >= 0 && states[i - 1][realQty - supplyArray[i]]) {
                selectedList.add(supplyArray[i]);
                realQty = realQty - supplyArray[i];
            }
        }
        if (realQty != 0) {
            selectedList.add(supplyArray[0]);
        }
        System.out.println("最优结果：" + res + "所选集合：" + selectedList);
    }




}
