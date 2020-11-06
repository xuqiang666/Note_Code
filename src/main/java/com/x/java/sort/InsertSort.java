package com.x.java.sort;

/**
 * @Author: 许庆之 on 2020/11/6.
 *  插入排序：直接插入排序 和 希尔排序
 */
public class InsertSort {

    /** 直接插入排序
     * 在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，
     * 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
     * 如此反复循环，直到全部排好顺序。
     * */
    public int[] insertSort(int[] a){
        int temp=0;
        for(int i=1;i<a.length;i++){
            int j=i-1;
            temp=a[i];
            /*从后向前遍历 比temp大的都往后移*/
            for(;j>=0&&temp<a[j];j--){
                a[j+1]=a[j];
            }
            a[j+1]=temp;
        }
        return a;
    }

    /**希尔排序  (最小增量排序)
     * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的 个数）分成若干组，
     * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，
     * 然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入 排序。
     * 当增量减到1时，进行直接插入排序后，排序完成。
     * */
    public int[] shellSort(int[] a){
        double d1=a.length;
        int temp=0;
        while(true){
            d1= Math.ceil(d1/2);
            int d=(int) d1;
            for(int x=0;x<d;x++){
                for(int i=x+d;i<a.length;i+=d){
                    int j=i-d;
                    temp=a[i];
                    for(;j>=0&&temp<a[j];j-=d){
                        a[j+d]=a[j];
                    }
                    a[j+d]=temp;
                }
            }
            if(d==1) break;
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        a = new InsertSort().insertSort(a);
        for (int value : a)
            System.out.print(value+" ");
    }
}
