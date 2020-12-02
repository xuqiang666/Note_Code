package com.x.designPattern.proxy;

/**
 * create by 许庆之 on 2020/2/26.
 */
public class Producer implements IProducer {

    @Override
    public double sale(double money) {
        System.out.println("执行了sale方法,money= "+money);
        return 0;
    }

    @Override
    public double buy(double money) {
        System.out.println("执行了buy方法,money= "+money);
        return 0;
    }
}
