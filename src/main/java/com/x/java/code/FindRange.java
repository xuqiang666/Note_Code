package com.x.java.code;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class FindRange {

    public int[] findRange(int[] nums, int target){

        //int[] numss = {5,7,7,8,8,8,8,8,8,10};
        int first = findFirst(nums,target);
        int last = findFirst(nums,target+1) -1;
        //当target大于所有num|| 当target小于所有num
        if(first == nums.length || nums[first] != target){
            return new int[] {-1,-1};
        }else{
            return new int[] {first,last};
        }
    }

    //用last也能做，用last = findFirst(nums,target+1) -1; 省去了使用last函数
    public int findLast(int[] nums, int target){
        int l = 0;
        int h = nums.length -1;
        while(l<=h){
            int m = l+(h-l)/2;
            if(nums[m] <= target) {
                l = m +1;
            } else {
                h = m-1;
            }
        }
        return h;
    }

    public int findFirst(int[] nums, int target){
        int l = 0;
        int h = nums.length -1;
        while(l<=h){
            int m = l+(h-l)/2;
            if(nums[m] < target) {
                l = m +1;
            } else {
                h = m-1;
            }
        }
        return l;
    }

    @Test
    public void test868(){
        int[] nums = {8,8,8,8,8,8};
        int[] ints = findRange(nums, 8);
        System.out.println(ints[0]+" "+ints[1]);
    }
}
