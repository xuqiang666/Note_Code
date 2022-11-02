package com.algorithm.dp;

/**
 * 121.买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] pirces = {7, 1, 5, 3, 6, 4};
        int i = maxProfit(pirces);
        System.out.println(i);
    }

    /**
     * 如果用low 表示历史最低点，那么price[i]-low就表示在第i天卖出股票能得到的利润
     * 那么就通过dp数组记录历史最低点
     * 用动态规划的思想，dp[i] = min(dp[i-1],prices[i])
     */
    public static int maxProfit(int[] prices) {
        int low = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            low = Math.min(low, prices[i-1]);
            max = Math.max(max,prices[i]-low);
        }
        return max;
    }

    public int maxProfitDP(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // 表示第i天持有股票所得最多现金
        int[] have = new int[len];
        // 表示第i天不持有股票所得最多现金
        int[] no = new int[len];
        // 此时的持有股票就一定是买入股票了
        have[0] = -prices[0];
        // 不持有股票那么现金就是0
        no[0] = 0;
        for (int i = 1; i < len; i++) {
            have[i] = Math.max(have[i-1], -prices[i]);
            no[i] = Math.max(no[i-1], prices[i] + have[i-1]);
        }
        return no[len - 1];
    }
}
