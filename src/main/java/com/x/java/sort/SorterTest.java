package com.x.java.sort;

import org.junit.Test;

import java.util.*;

/**
 * create by 许庆之 on 2020/8/6.
 * @author 许庆之
 */
public class SorterTest {

    //调用Sorter排序 快排
    @Test
    public void test02(){
        Integer[] a= {4,2,14,1,12,6,8,13,6,9,12};
        Sorter quickSorter = new QuickSorter();
        quickSorter.sort(a);
        for(int i=0;i<=a.length-1;i++){
            System.out.print(a[i]+" ");
        }

        List<Integer> list = new ArrayList<>(Arrays.asList(a));
        int i = Collections.binarySearch(list, 6);
        System.out.println(i);
    }

    //  冒泡排序
    @Test
    public void test03(){
        //泛型必须匹配对象类型，不能为基本类型int
        Integer[] a= {4,2,14,1,12,6,8,13,6,9,12};
        Sorter quickSorter = new BubbleSorter();
        quickSorter.sort(a);
        for(int i=0;i<=a.length-1;i++){
            System.out.print(a[i]+" ");
        }
    }

    //传入比较器的形式
    @Test
    public void test04(){
        //泛型必须匹配对象类型，不能为基本类型int
        Integer[] a= {4,2,14,1,12,6,8,13,6,9,12};
        Sorter quickSorter = new QuickSorter();
        quickSorter.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for(int i=0;i<=a.length-1;i++){
            System.out.print(a[i]+" ");
        }
    }
}

