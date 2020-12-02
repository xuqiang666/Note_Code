package com.x.designPattern.singleton;

/**
 * @Author: 许庆之 on 2020/12/2.
 */
public enum SingletonEnum {

    /**
     * 实例
     */
    INSTANCE;

    /**
     * 调用方式： SingletonEnum.INSTANCE.doSomething();
    * */
    public void doSomething() {
        System.out.println("doSomething");
    }

}
