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

    /**
    在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]
    */
    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            /*array==null：数组为空
            array.length==0：行为0，即 array = []
            array.length==1 && array[0].length == 0：行为1，列为0，即 array = [[]]]
            */
            if(matrix==null||matrix.length==0||matrix[0].length==0) {
                return false;
            }
            int m = matrix.length - 1;
            // 此处注意防止数组为空时下表越界问题
            int n = matrix[0].length;
            //从二维数组的右上角看下来就是一个BinarySearchTree
            int i = 0, j = n-1;
            while (i <= m && j >= 0) {
                if (target < matrix[i][j]) {
                    j--;
                } else if (target > matrix[i][j]) {
                    i++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
