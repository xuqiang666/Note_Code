package com.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: 许庆之 on 2020/10/10.
 */
public class Test {
    public void quickSort(int[] in) {
        sortCur(in, 0, in.length - 1);
    }

    private void sortCur(int[] in, int start, int end) {
        if (start < end) {
            int p = core(in, start, end);
            sortCur(in, start, p - 1);
            sortCur(in, p + 1, end);
        }
    }

    private int core(int[] in, int start, int end) {
        int j = start;
        int x = in[end];
        for (int i = start; i < end; i++) {
            if (in[i] < x) {
                swap(in, i, j);
                j++;
            }
        }
        swap(in, j, end);
        return j;
    }

    private void swap(int[] in, int i, int j) {
        int temp = in[j];
        in[j] = in[i];
        in[i] = temp;
    }

    @org.junit.Test
    public void test1345() {
        int[] a = {4, 2, 14, 1, 12, 6, 8, 13, 6, 9, 12};
        quickSort(a);
        for (int i = 0; i <= a.length - 1; i++) {
            System.out.print(a[i] + " ");
        }
    }

    @org.junit.Test
    public void generalHashValue() {
        Set<String> set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        int result = 17;

        String attr = null;
        for (Iterator i$ = set.iterator(); i$.hasNext(); result = 37 * result + (attr == null ? 0 : attr.hashCode())) {
            System.out.println(result);
            System.out.println(attr == null ? 0 : attr.hashCode());
            attr = (String) i$.next();
        }

        System.out.println(result);

    }

}
