package com.y;

import java.util.*;


class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param L int整型
     * @param R int整型
     * @param x int整型
     * @return int整型
     */
    public static int countNum(int L, int R, int x) {
        // write code here
        int n = 0;
        char[] chars = String.valueOf(x).toCharArray();
        char x1 = chars[0];
        for (int i = L; i <= R; i++) {
            String str = String.valueOf(i);
            for (char c : str.toCharArray()) {
                if (c == x1) {
                    n++;
                }
            }

        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(validNum(2));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param N int整型 字符串的长度
     * @return int整型
     */
    public static int validNum(int N) {
        // write code here
        int[][] dp = new int[N + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0];
        }
        return dp[N][0] + dp[N][1];
    }
}