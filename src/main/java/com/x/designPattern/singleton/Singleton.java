package com.x.designPattern.singleton;

/**
 * @Author: 许庆之 on 2020/12/2.
 *
 *  volatile + 双重循环校验 实现单例模式
 */
public class Singleton {

    private Singleton() {
    }

    private static volatile Singleton instance = null;

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
