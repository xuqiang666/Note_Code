package com.x.java.BinarySearch;

import java.util.Comparator;

/**
 * Create By  xqz on 2020/8/6.
 */
public class BinarySearcher implements Searcher {

    /**
     * 使用循环和自然排序
     * @param list list 待查找的数组
     * @param key key 带查找的元素
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> int search(T[] list, T key) {
        int low = 0;
        int high = list.length-1;
        while( low <= high ){
            int mid = (low+high)>>>1;
            int cmp = list[mid].compareTo(key);
            if (cmp == 0){
                return mid;
            }
            else if (cmp<0){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 使用递归和比较器
     * @param list list 待查找的数组
     * @param key   key 待查找的元素
     * @param comp  comp 比较器
     * @param <T>
     * @return
     */
    @Override
    public <T> int search(T[] list, T key, Comparator<T> comp) {
        return searcher(list,0,list.length-1,key,comp);
    }

    private <T> int searcher(T[] list,int low,int high,T key,Comparator<T> comp){
        if (low<high){
            int mid = low + ((high - low)>>1);
            int cmp = comp.compare(list[mid], key);
            if (cmp<0){
                searcher(list,low,mid-1,key,comp);
            }
            else if(cmp>0){
                searcher(list,mid+1,high,key,comp);
            }
            else {
                return mid;
            }
        }
        return -1;
    }
}
