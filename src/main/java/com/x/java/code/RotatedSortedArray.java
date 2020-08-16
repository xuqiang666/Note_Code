package com.x.java.code;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 * Input: [3,4,5,1,2],
 * Output: 1
 */
public class RotatedSortedArray {

    public int rotatedSortedArray(int[] nums){
        int l = 0;
        int h = nums.length-1;
        while(l<=h){
            int m = l+(h-l)/2;
            if(nums[m] > nums[h]) {
                l = m +1;
            } else {
                h = m - 1;
            }
        }
        return nums[l];
    }

    @Test
    public void test53464(){
        int [] nums = {5,6,7,8,24,1,2,3,4};
        System.out.println(rotatedSortedArray(nums));
    }
}
