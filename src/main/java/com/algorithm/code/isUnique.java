package com.algorithm.code;

import org.junit.Test;

/**
 * Create By  xqz on 2020/8/29.
 */
public class isUnique {
    class Solution {
        public boolean isUnique(String astr) {
            //char[] data = astr.toCharArray();
            //return astr.chars().distinct().count() == astr.length();

//            HashSet<Character> set = new HashSet<>();
//            for (int i = 0; i < astr.length(); i++) {
//                if (!set.add(astr.charAt(i))){
//                    return false;
//                }
//            }
//            return true;

            for (int i = 0; i < astr.length(); i++) {
                if (astr.lastIndexOf(astr.charAt(i))!=i){
                    return false;
                }
            }
            return true;
        }
    }
    @Test
    public void test01(){
        Boolean l = new Solution().isUnique("asdfghj");
        System.out.println(l);
    }
}
