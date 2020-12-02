package com.x.designPattern.singleton;

/**
 * @Author: 许庆之 on 2020/12/2.
 *
 * mybatis 中的ErrorContext 的实现
 * ThreadLocal封装的单例，实现每个线程唯一单例
 */
public class ErrorContext {

    private ErrorContext(){
    }

    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<>();

    public ErrorContext instance(){
        ErrorContext context = LOCAL.get();
        if (context == null){
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }
}
