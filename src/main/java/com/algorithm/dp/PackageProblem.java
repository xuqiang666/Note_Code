package com.algorithm.dp;

import org.junit.Test;

/**
 * 背包问题
 */
public class PackageProblem {

    @Test
    public void testPackage() {
        System.out.println("最终结果是：" + packageProblem());
    }

    /**
     * 背包问题是动态规划中最经典的一道算法题。背包问题的种类比较多，我们先来看一个最简单的背包问题-基础背包。他是这样描述的。
     * 有N件物品和一个容量为V的包，第i件物品的重量是w[i]，价值是v[i]，求将哪些物品装入背包可使这些物品的重量总和不能超过背包容量，且价值总和最大。
     * 我们先来举个例子分析一下
     * 假设我们背包可容纳的重量是4kg，有3样东西可供我们选择，一个是高压锅有4kg，价值300元，一个是风扇有3kg，价值200元，最后一个是一双运动鞋有1kg，价值150元。
     * 问要装哪些东西在重量不能超过背包容量的情况下价值最大。如果只装高压锅价值才300元，
     * 如果装风扇和运动鞋价值将达到350元，所以装风扇和运动鞋才是最优解
     * <p>
     * dp[i][j] = max----dp[i-1][j] (不选择当前物品)
     * ----当前商品的价值 + 剩余空间可容纳的价值（选择当前商品） = value[i] + dp[i-1][j-weight[i]]
     */
    public static int packageProblem() {
        //包最大可装重量
        int maxWeight = 4;
        //3个物品的重量
        int[] weights = {1, 4, 3};
        //3个物品的价值
        int[] value = {150, 300, 200};

        // i 表示物品数量，按层往下一个一个增进来，j 表示最大重量，数组的值表示当前物品在最大重量的最大价值。由他们不断往下增加在最后能得到所有物品在背包重量到最大时的最大价值
        // 这里为什么要选择从1开始？？这样避免后面dp[i-1]越界
        int[][] dp = new int[weights.length + 1][maxWeight + 1];
        for (int i = 1; i <= value.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + value[i - 1]);
                } else {
                    // j<weights[i-1]  这一格的最大重量都不可能放入当前物品
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weights.length][maxWeight];
    }

    /**
     * 背包问题-空间优化
     * 给上面的方法做空间优化
     * 这里注意一定要让j 逆序遍历，因为dp[j]的值依赖于他前面的值，如果顺序遍历前面的值已经被修改了后面的值计算时就拿不到上一层的数据了
     */
    public static int packageProblemSpaceOptimization() {
        int maxWeight = 4;
        int[] weights = {1, 4, 3};
        int[] value = {150, 300, 200};
        int[] dp = new int[maxWeight + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int j = maxWeight; j >= 1; j--) {
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[maxWeight];
    }

    /**
     * 背包问题--二维    LeetCode第474题   https://mp.weixin.qq.com/s/nXjh9dLRJj-33i5vRoFHUQ
     * <p>
     * 给你一个二进制字符串数组(仅由'0'和'1'组成)strs和两个整数m和n。请你找出并返回strs的最大子集的长度，该子集中最多有m个0和n个1。
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
     * <p>
     * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
     * <p>
     * 这题让计算从字符串数组中选择最多的字符串，并且所选择的所有字符串中0的个数不能超过m，1的个数不能超过n。
     * 如果这题只要求选择0的个数不能超过m，那么很明显是一道简单的背包问题，但这题限制的维度有两个，我们仍然可以使用背包问题来解决。
     * <p>
     * 定义dp[i][j][k]表示选择前i个字符串放到一个最多能容纳j个0和k个1的背包中，所能选择字符串的最大个数。
     * 所以当我们考虑第i个字符串的时候需要先计算第i个字符串中0和1的个数，如果能放到背包中，我们可以选择放也可以选择不放，我们取最大值即可。
     * dp[i][j][k] = max(dp[i-1][j][k] , dp[i][j-zeros[i]][k-ones[i]] + 1)
     */
    public int packageProblemTwoDimension(String[] strs, int m, int n) {
        int length = strs.length;
        // 有一个可以放m个0和n个1的背包，最多可以放字符串的数量
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            String str = strs[i];
            int zeros = 0;
            int ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else if (c == '1') {
                    ones++;
                }
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[length][m][n];
    }


}
