package com.x.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * create by 许庆之 on 2020/8/6.
 * 反射工具类， 根据target set和get属性
 */
public class ReflectionUtil {

    private ReflectionUtil(){
        throw new AssertionError();
    }

    //get
    public static Object getValue(Object target,String fieldName){
        Class<?> clazz = target.getClass();
        String[] fields = fieldName.split("\\.");

        try{
            for (int i = 0; i < fields.length - 1; i++) {
                Field f = clazz.getDeclaredField(fields[i]);
                f.setAccessible(true);
                target = f.get(target);
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fields[fields.length-1]);
            f.setAccessible(true);
            return f.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //set
    public static void setValue(Object target,String fieldName,Object value) throws NoSuchFieldException {
        Class<?> clazz = target.getClass();
        String[] fields = fieldName.split("\\.");

        try{
            for (int i = 0; i < fields.length - 1; i++) {
                Field f = clazz.getDeclaredField(fields[i]);
                f.setAccessible(true);
                Object val = f.get(target);
                if(val == null){
                    Constructor<?> constructor = f.getType().getDeclaredConstructor();
                    constructor.setAccessible(true);
                    val = constructor.newInstance();
                    f.set(target,val);
                }
                target = val;
                clazz = target.getClass();
            }
            Field f = clazz.getDeclaredField(fields[fields.length-1]);
            f.setAccessible(true);
            f.set(target,value);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
