package com.x.java.code;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 * 一个数 x 的开方 sqrt 一定在 0 ~ x 之间，并且满足 sqrt == x / sqrt。可以利用二分查找在 0 ~ x 之间查找 sqrt。
 */
public class Sqrt {

    public int sqrt(int x){
        if(x<=1) {
            return x;
        }
        int l = 1, h = x;
        while(l<=h){
            int mid = l+(h-l)/2;
            if(mid*mid == x) {
                return mid;
            }
            if(mid*mid < x){
                l = mid + 1;
            }else{
                h = mid - 1;
            }
            System.out.println(mid+"   "+l+"   "+h);
        }
        //循环条件为l<=h，在循环结束时h永远是比l小1，如当l=3,h=2,此时应该返回2，所以此处返回h
        return h;
    }
    @Test
    public void test901(){
        int[] array = {1,2,3,4,5,6,7,8,9,14,56,77};
        System.out.println(sqrt(8));
    }
}
