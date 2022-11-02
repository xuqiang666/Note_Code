package com.pratice;

public class Example {

    private String str = "adad";
    private String[] strAr= {"a","b","c"};

    public static void main(String[] args) {
        Example example = new Example();
        example.change(example.str,example.strAr);
        System.out.println(example.str+"  &  "+example.strAr[0]);
    }

    private void change(String str,String[] strAr){
        str = "changed";
        strAr[0] = "z";
    }
}
