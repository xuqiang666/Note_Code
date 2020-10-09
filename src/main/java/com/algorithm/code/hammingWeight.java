package com.algorithm.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create By  xqz on 2020/9/20.
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
 */


public class hammingWeight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new Solution().hammingWeight(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public static class Solution {
        // you need to treat n as an unsigned value
        // n&(n-1) 计算二进制中 1 的个数
        public int hammingWeight(int n) {
            int count = 0;
            // 大数错误11111111111111111111111111111101
            // while(n>0){
            //     n=n&(n-1);
            //     count++;
            // }
            // return count;

            //
            while(n!=0){
                count+=(n&1);
                n>>>=1;
            }
            return count;
        }
    }
}
