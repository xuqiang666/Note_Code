package com.algorithm.code;

import org.junit.Test;

/**
 * create by 许庆之 on 2020/8/3.
 * 递归实现字符串反转
 */
public class Reverse {
    public static void main(String[] args) {
        String str = "abcd";
        System.out.println(reverse(str));
    }

    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

    @Test
    public void numberReverse() {
        int a = 1234;
        System.out.println(reverseInt(a));
    }

    public int reverseInt(int n) {
        int m = 0;
        while (n > 0) {
            m = m * 10 + n % 10;
            n = n / 10;
        }
        return m;
    }
}
