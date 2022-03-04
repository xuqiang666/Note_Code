package com.algorithm.dp;

import org.junit.Test;

/**
 * 背包问题
 */
public class PackageProblem {

    @Test
    public static void testPackage(String[] args) {
        System.out.println("最终结果是：" + packageProblem1());
    }

    public static int packageProblem1() {
        //包最大可装重量
        int maxWeight = 4;
        //3个物品的重量
        int[] weights = {1, 4, 3};
        //3个物品的价值
        int[] value = {150, 300, 200};

        // 这里为什么要选择从1开始？？这样避免后面dp[i-1]越界
        int[][] dp = new int[weights.length+1][maxWeight+1];
        for (int i = 1; i <= value.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (j>=weights[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weights[i-1]]+value[i-1]);
                }else {
                    // j<weights[i-1]  这一格的最大重量都不可能放入当前物品
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[weights.length][maxWeight];
    }
}
