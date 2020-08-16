package com.x.thread;

/**
 * create by 许庆之 on 2020/2/10.
 */
public class Demo {

    public static void main(String[] args) {


        Thread t = new Thread() {
            @Override
            public void run() {
                pong();
            }
        };
        t.run();
        System.out.println("Ping");
    }

    public static void pong() {
        System.out.println("Pong");
    }



}
