package com.pratice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            int len = str.length();
            int count = 0;
            int max = 0;
            for (int i = 0; i < len; i++) {
                int te = i;
                while (true) {
                    if (te - 1 < 0 || te + 1 >= len) {
                        break;
                    }
                    if (str.charAt(te) == str.charAt(++te)) {
                        count++;
                    } else {
                        break;
                    }
                }
                max = Math.max(count, max);
                count = 0;
            }
            System.out.println(max);
        }
    }


}
