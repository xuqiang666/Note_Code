package com.x.designPattern.singleton;

/**
 * @Author: 许庆之 on 2020/12/2.
 */
public class SingletonStatic {

    private static class SingletonHolder {
        private static SingletonStatic instance = new SingletonStatic();
    }

    private SingletonStatic() {

    }

    public static SingletonStatic getInstance() {
        return SingletonHolder.instance;
    }
}
