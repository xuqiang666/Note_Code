package com.x.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * create by 许庆之 on 2020/2/26.
 */
public class testProducer {

    public static void main(String[] args) {
        final Producer producer = new Producer();

        /**基于接口的动态代理
        涉及的类：Proxy
        提供者：JDK官方
                如何创建代理对象
        使用Proxy中的newProxyInstance方法
                创建代理对象的要求
        被代理类最少实现一个接口，如果没有则不能使用
                newProxyInstance方法的参数
        ClassLoader：类加载器
        用于加载代理对象字节码，和被代理对象使用相同的类加载器（固定用法）
        Class[]  ：字节码数组
        它是用于让代理对象和被代理对象拥有相同的方法（固定写法）
        InvocationHandler：用于提供增强的代码
        它是让我们如何写代理，一般使用改接口的实现类，通常情况下都是匿名内部类，但不是必须的
                此接口的实现类都是谁用谁写*/

        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(Producer.class.getClassLoader(), Producer.class.getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 执行被代理对象的所有方法都会经过此方法
                     * proxy     代理对象的引用
                     * method   当前执行的方法
                     * args     当前执行方法所需的参数
                     * return   和被代理对象有相同的返回值
                     * */

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object returnValue = null;

                        //获取参数
                        Double newMoney = (Double) args[0];
                        //获取方法的名字
                        if ("sale".equals(method.getName())) {
                            returnValue = method.invoke(producer, newMoney*2.5d);
                        }
                        return returnValue;
                    }
                });
        proxyProducer.sale(100d);

    }
}
