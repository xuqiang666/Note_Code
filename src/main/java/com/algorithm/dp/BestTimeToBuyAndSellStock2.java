package com.algorithm.dp;

/**
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 */
public class BestTimeToBuyAndSellStock2 {

    public static void main(String[] args) {
        int[] pirces = {7, 1, 5, 3, 6, 4};
        int i = maxProfit2Ext(pirces);
        System.out.println(i);
    }

    /**
     * 用动态规划的思想
     * 使用 dp[i][0] 表示第i天不持有股票的钱；dp[i][1]表示第i天持有股票的钱
     * dp[i][0] = max(dp[i-1][0] , dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1] , dp[i-1][0] - prices[i])
     */
    public static int maxProfit2(int[] prices) {
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i-1][0] , dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1] , dp[i-1][0] - prices[i]);
        }
        return dp[length-1][0];
    }

    /**
     * 每一天的状态只与前一天的状态有关，而与更早的状态都无关，因此我们不必存储这些无关的状态，
     * 只需要将 dp[i-1][0]和 dp[i−1][1] 存放在两个变量中，
     * 通过它们计算出 dp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1 天的状态转移即可。
     */
    public static int maxProfit2Ext(int[] prices) {
        int length = prices.length;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 1; i < length; i++) {
            dp0 = Math.max(dp0 , dp1 + prices[i]);
            dp1 = Math.max(dp1 , dp0 - prices[i]);
        }
        return dp0;
    }
}
