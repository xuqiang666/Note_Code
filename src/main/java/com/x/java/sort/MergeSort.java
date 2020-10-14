package com.x.java.sort;

import java.util.Arrays;

/**
 * @Author: 许庆之 on 2020/10/14.
 *
 *      二路归并排序
 */
public class MergeSort {
    public void mergeSort(int[] a,int start,int end){
        if(start==end){
            return;
        }
        int mid = start+(end-start)/2;
        mergeSort(a,start,mid);
        mergeSort(a,mid+1,end);
        merge(a,start,mid,end);
    }

    private void merge(int[] a,int left,int mid,int right){
        int p1=left,p2=mid+1,k=left;
        int[] res = new int[a.length];
        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2]){
                res[k++]=a[p1++];
            }else{
                res[k++]=a[p2++];
            }
        }
        while(p1<=mid){
            res[k++]=a[p1++];
        }
        while(p2<=right){
            res[k++]=a[p2++];
        }
        for (int i = left; i <= right; i++) {
            a[i]=res[i];
        }
    }

    public static void main(String[] args) {
        int[] ints = {2, 3, 4, 56, 2, 23, 5, 7, 32, 1,};
        int len = ints.length;
        new MergeSort().mergeSort(ints,0,len-1);
        for (int i : ints) {
            System.out.print(i+" ");
        }
    }
}
