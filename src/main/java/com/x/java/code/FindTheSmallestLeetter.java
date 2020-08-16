package com.x.java.code;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 * 给定一个有序的字符数组 letters 和一个字符 target，要求找出 letters 中大于 target 的最小字符，如果找不到就返回第 1 个字符。
 */
public class FindTheSmallestLeetter {

    public char findTheSmallestLeetter(char[] letters , char target){

        int h = letters.length -1;
        int l = 0;
        while(l<=h){
            int mid = l+(h-l)/2;
            if(letters[mid]==target) {
                return letters[mid+1];
            } else if(letters[mid] < target) {
                l = mid + 1;
            } else {
                h = mid -1;
            }
        }
        //当target大于所有字符时，返回找不到
        return l<letters.length? letters[l] : letters[0];
    }

    @Test
    public void test342(){
        char [] letters = {'a','b','c','f','g','h','n','p','s','t','x'};
        System.out.println(findTheSmallestLeetter(letters,'c'));
    }
}
