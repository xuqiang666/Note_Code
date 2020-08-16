package com.x.proxy;

/**
 * create by 许庆之 on 2020/2/26.
 */
public class testProducer2 {

    /*public static void main(String[] args) {
        final Producer producer = new Producer();

        *//**基于子类的动态代理
         涉及的类：Enhancer
         提供者：第三方
         如何创建代理对象
         使用Enhancer类中的Create方法
         创建代理对象的要求
         被代理类不能是最终类，即必须有子类
         Create方法的参数
         Class ：字节码
         用于指定被代理对象的字节码
         CallBack：用于提供增强的代码
         它是让我们如何写代理，一般使用该接口的实现类，通常情况下都是匿名内部类，但不是必须的
         一般使用改接口的子接口实现类： MethodInterceptor
         *//*

        Producer cglibProducer = (Producer)Enhancer.Create(Producer.class.getClassLoader(), Producer.class.getInterfaces(),
                new MethodInterceptor() {
                    *//**
                     * 执行被代理对象的所有方法都会经过此方法
                     * proxy     代理对象的引用
                     * method   当前执行的方法
                     * args     当前执行方法所需的参数
                     * methodProxy 当前执行方法的代理对象
                     * return   和被代理对象有相同的返回值
                     * *//*

                    @Override
                    public Object intercepter(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

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

    }*/
}
