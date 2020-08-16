package com.x.java.sort;

import java.util.Comparator;

/**
 * create by 许庆之 on 2020/8/6.
 */
public class QuickSorter implements Sorter {

    @Override
    public <T extends Comparable<T>> void sort(T[] list) {
        sorter(list,0,list.length-1);
    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comp) {
        sorterX(list,0,list.length-1,comp);
    }

    private <T extends Comparable<T>> void sorter(T[] a, int start, int end){
        if(start<end){
            int p = core(a,start,end);
            sorter(a,start,p-1);
            sorter(a,p+1,end);
        }
    }

    private <T extends Comparable<T>> int core(T[] a, int start, int end){
        T x  = a[end];
        int j = start;
        for (int i = start; i < end; i++) {
            if(a[i].compareTo(x) >= 0){
                swap(a,i,j);
                j++;
            }
        }
        swap(a,j,end);
        return j;
    }

    private <T> void swap(T[] a, int x, int y){
        T temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private <T> void sorterX(T[] a, int start, int end,Comparator<T> comp){
        if(start<end){
            int p = coreX(a,start,end,comp);
            sorterX(a,start,p-1,comp);
            sorterX(a,p+1,end,comp);
        }
    }
    private <T> int coreX(T[] a, int start, int end,Comparator<T> comp){
        T x  = a[end];
        int j = start;
        for (int i = start; i < end; i++) {
            if(comp.compare(a[i], x) >= 0){
                swap(a,i,j);
                j++;
            }
        }
        swap(a,j,end);
        return j;
    }
}
