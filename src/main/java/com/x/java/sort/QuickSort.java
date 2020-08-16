package com.x.java.sort;

import org.junit.Test;

/**
 * create by 许庆之 on 2020/2/11.
 *
 * 快排
 */
public class QuickSort {

    @Test
    public void test(){
        int[] a= {4,2,14,1,12,6,8,13,6,9,12};
        Sort(a,0,a.length-1);
        for(int i=0;i<=a.length-1;i++){
            System.out.print(a[i]+" ");
        }
    }



    private static void Sort(int[] a, int start, int end){
        if(start<end) {
            int p = Core(a, start, end);
            Sort(a, start, p - 1);
            Sort(a, p+1, end);
        }
    }

    private static int Core(int[] a, int start, int end) {
        int x = a[end];
        int j = start;
        //左边都是比x大的
        for(int i=start;i<end;i++)
        {
            if(a[i] >= x)
            {
                swap(a,i,j);
                j++;
            }
        }
        swap(a,j,end);
        return j;
    }

    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
        /*for(int i=0;i<=a.length-1;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println("   "+x+" "+y);*/
    }


}
