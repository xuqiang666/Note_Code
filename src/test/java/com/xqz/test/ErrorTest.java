package com.xqz.test;

import org.junit.Test;

public class ErrorTest{


    public static void main(String[] args) {
        try {
            fun();
        } catch (ClassNotFoundException e) {
            // exception handler
        }
        fun0();
    }

    public static void fun() throws ClassNotFoundException {
        try {
            int a = 1;
            int b = a/0;
        }catch (Exception e){
            throw new ClassNotFoundException();
        }
    }

    public static void fun0() {
        try {
            int a = 1;
            int b = a/0;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 测试 finally中的throw
     */
    @Test
    public void test345() throws Exception{
        try {
            int a = 0;
            int b = a/0;
        } catch (Exception e) {
            System.out.println("catch-01");
            throw new ClassNotFoundException("catch-exception");
        } finally {
            System.out.println("finally-02");
            throw new ClassCastException("finally-exception");
        }
    }
}
