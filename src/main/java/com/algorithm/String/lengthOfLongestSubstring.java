package com.algorithm.String;

import java.util.HashMap;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * <p>
 * 滑动窗口解决  https://mp.weixin.qq.com/s/fHpCyEOAl_QW4v3GIZUh1Q
 */
public class lengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        // (1) 先创建map数据结构存储（k,v）,其中key为字符，value为字符的下标.
        HashMap<Character, Integer> map = new HashMap<>();
        //(2)定义不重复子串的开始位置为start,结束位置为end. start 刚开始不动，end向后移动
        int max = 0, start = 0;
        //(3) 遍历字符串
        for (int end = 0; end < s.length(); end++) {
            // (4)取出每个字符
            char ch = s.charAt(end);
            // (5)判断字符，hashmap中没有是否包含该字符
            if (map.containsKey(ch)) {
                //  (6)包含字符，将start放在上一个重复字符位置的后一位。
                start = Math.max(start, map.get(ch) + 1);
            }
            // (6)计算max长度
            max = Math.max(max, end - start + 1);
            //(7)将字符作为key存入map,下标存入value中
            map.put(ch, end);

        }
        // (8)程序执行结束，返回无重复子串最大长度。
        return max;
    }
}
