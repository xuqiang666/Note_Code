package com.x.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Create By  xqz on 2020/8/17.
 */
public class MapperProxy implements InvocationHandler {

    /**
     * 利用Proxy.newProxyInstance 获取代理类
     * @param clz  目标接口
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[] { clz }, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //判断method是否是Object类中定义的方法
        //如 hashCode()、toString()、equals()
        //如果是则将target 指向 this
        //如果直接调用 method.invoke 会报空指针错误
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
            }
        }
        // 投鞭断流
        //  mybatis mapper底层实现原理
        return findUserById((Integer) args[0]);

    }

    private User findUserById(Integer id) {
        return new User(id, "zhangsan", 18);
    }



    @Test
    public void test003(){
        MapperProxy mapperProxy = new MapperProxy();
        UserMapper userMapper = mapperProxy.newInstance(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println("ID:" + user.getId());
        System.out.println("Name:" + user.getName());
        System.out.println("Age:" + user.getAge());
        System.out.println(userMapper.toString());
    }
    //ID:1
    //Name:zhangsan
    //Age:18
    //com.x.proxy.MapperProxy@46f7f36a

    /** mybatis 源码实现
     *  @Override
     * public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
     *   try {
     *     if (Object.class.equals(method.getDeclaringClass())) {
     *       return method.invoke(this, args);
     *     } else if (isDefaultMethod(method)) {
     *       return invokeDefaultMethod(proxy, method, args);
     *     }
     *   } catch (Throwable t) {
     *     throw ExceptionUtil.unwrapThrowable(t);
     *   }
     *   final MapperMethod mapperMethod = cachedMapperMethod(method);
     *   return mapperMethod.execute(sqlSession, args);
     * }
     */
}
