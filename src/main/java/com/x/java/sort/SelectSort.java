package com.x.java.sort;

/**
 * @Author: 许庆之 on 2020/11/6.
 *  选择排序：简单选择排序和堆排序
 */
public class SelectSort {

    /**简单选择排序：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     *  然后在剩下的数当中再找最小的与第二个位置的数交换，
     *  如此循环到倒数第二个数和最后一个数比较为止。
     *  */
    public int[] selectSort(int[] a) {
        int position = 0;
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            position = i;
            int temp = a[i];
            for (; j < a.length; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    position = j;
                }
            }
            a[position] = a[i];
            a[i] = temp;
        }
        return a;
    }

    public static void main(String[] args){
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        a = new SelectSort().selectSort(a);
        for (int value : a)
            System.out.print(value+" ");
    }
}
