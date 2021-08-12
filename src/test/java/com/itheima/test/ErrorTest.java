package com.itheima.test;

import lombok.SneakyThrows;

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
}
