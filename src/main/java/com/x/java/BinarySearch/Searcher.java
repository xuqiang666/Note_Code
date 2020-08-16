package com.x.java.BinarySearch;

import java.util.Comparator;

/**
 * Create By  xqz on 2020/8/6.
 */
public interface Searcher {

    /**
     * 自然排序
     * @param list list 待查找的数组
     * @param key key 带查找的元素
     */
    public <T extends Comparable<T>> int search(T[] list, T key);

    /**
     * 带比较器的排序
     * @param list list 待查找的数组
     * @param key   key 待查找的元素
     * @param comp  comp 比较器
     */
    public <T> int search(T[] list, T key, Comparator<T> comp);
}
