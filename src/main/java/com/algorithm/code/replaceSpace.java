package com.algorithm.code;

import org.junit.Test;

/**
 * Create By  xqz on 2020/9/6.
 */
public class replaceSpace {
    class Solution {
        public String replaceSpace(String s) {
            /*使用StringBuilder 避免在循环中对String进行+拼接 */
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    sb.append("%20");
                } else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }

        /*因为String是不可变的，相对string进行修改必会引入新的空间
         * 如果入参改为StringBuilder 则能实现不引入新的空间进行修改
         * */
        public String replaceSpace(StringBuilder str) {
            int len1 = str.length() - 1;
            for (int i = 0; i <= len1; i++) {
                if (str.charAt(i) == ' ') {
                    str.append("  ");
                }
            }
            int len2 = str.length() - 1;
            while (len2 > len1 && len1 >= 0) {
                char c = str.charAt(len1--);
                /*从后往前写，不影响数据*/
                if (c == ' ') {
                    str.setCharAt(len2--, '0');
                    str.setCharAt(len2--, '2');
                    str.setCharAt(len2--, '%');
                } else {
                    str.setCharAt(len2--, c);
                }
            }
            return str.toString();
        }
    }

    @Test
    public void test() {
        StringBuilder sb = new StringBuilder("how are you");
        String s = new Solution().replaceSpace(sb);
        System.out.println(s);
    }

}