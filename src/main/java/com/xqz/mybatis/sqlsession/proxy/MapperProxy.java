package com.xqz.mybatis.sqlsession.proxy;

import com.xqz.mybatis.cfg.Mapper;
import com.xqz.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author
 */
public class MapperProxy implements InvocationHandler {

    //map的key是全限定类名+方法名
    private Map<String,Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String,Mapper> mappers,Connection conn){
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 用于对方法进行增强的，我们的增强其实就是调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //3.组合key
        String key = className+"."+methodName;
        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);

        //System.out.println(mapper.getQueryString());
        //System.out.println(mapper.getResultType());
        //5.判断是否有mapper
        if(mapper == null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        //6.调用工具类执行查询所有  因为Executor.selectList不是静态方法，所以在此处调用必须先new
        return new Executor().selectList(mapper,conn);

    }
}
