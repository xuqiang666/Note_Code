package com.x.java.BinarySearch;


import org.junit.Test;

/**
 * create by 许庆之 on 2020/4/15.
 * 二分查找也称为折半查找，每次都能将查找区间减半，这种折半特性的算法时间复杂度为 O(logN)。
 */
public class BinarySearchBasic {

    public int binarysearch(int key , int [] array){
        int l=0, h=array.length-1;
        while(l<=h){

            //此处实际理解为(h+l)/2，但加法可能造成溢出，化为减法
            int mid = l + (h-l)/2;
            if(key==array[mid]) {
                return mid;
            }
            if(key<array[mid]){
                h = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int[] array = {1,2,3,4,5,6,7,8,9,14,56,77};
        System.out.println(binarysearch(77,array));
    }

    @Test
    public void test1(){
        Integer[] array = {1,2,3,4,5,6,7,8,9,14,56,77};
        Searcher searcher = new BinarySearcher();
        int num = searcher.search(array,77);
        System.out.println(num);
    }
}
