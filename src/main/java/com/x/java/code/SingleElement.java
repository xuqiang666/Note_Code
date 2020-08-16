package com.x.java.code;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 * Input: [1, 1, 2, 3, 3, 4, 4, 8, 8]
 * Output: 2
 * 一个有序数组只有一个数不出现两次，找出这个数。
 * 要求以 O(logN) 时间复杂度进行求解，因此不能遍历数组并进行异或操作来求解，这么做的时间复杂度为 O(N)。
 *因为 h 的赋值表达式为 h = m，那么循环条件也就只能使用 l < h 这种形式。
 */
public class SingleElement {

    public int singleElement(int[] nums){
        int l = 0;
        int h = nums.length-1;
        while(l<h){
            int m = l+(h-1)/2;
            //保证m在偶数位
            if(m % 2 == 1){
                m--;
            }
            if(nums[m] != nums[m+1]) {
                h = m;
            } else {
                l = m +2;
            }
        }
        return nums[l];
    }

    @Test
    public void test343(){
        int[] nums ={1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println(singleElement(nums));
    }
}
