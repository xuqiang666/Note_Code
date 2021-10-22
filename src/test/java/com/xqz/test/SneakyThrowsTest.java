package com.xqz.test;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * lombok 的 @SneakyThrows 处理受检异常（编译时异常）
 * 将当前方法抛出的异常，包装成 Runtime Exception，骗过编译器，使得调用点可以不用显式的处理异常信息
 */
public class SneakyThrowsTest {

    /**
     * java的类型转换要求：当父类强转为子类类型时，要求 **父类引用指向的对象的类型是子类的时候才可以进行强制类型转换**
     * 如 ： Animal a1 = new Dog();  Dog d1 = (Dog)a1;   前提是a1指向的对象实例就为Dog类型
     * 也可以先使用 instanceof 进行类型判断，在进行强转
     */
    @Test
    public void ThrowsTest() {
        try {
            throw new Exception();
        } catch (Throwable e) {
            // 直接将e强转为RuntimeException，运行到这里会报类型转换异常。
            //assert e instanceof RuntimeException;
            throw (RuntimeException) e;
        }
    }

    @Test
    public void test434() {
        System.out.println(utf8ToString(new byte[]{}));
    }

    public String utf8ToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 使用 @SneakyThrows 就不用手动处理编译时异常啦
     */
    @Test
    public void test() {
        System.out.println(sneakyThrowsTest0(new byte[]{}));
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public static String sneakyThrowsTest(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }


    /**
     * 注解的实现原理
     */
    public static String sneakyThrowsTest0(byte[] bytes) {
        try {
            if (bytes.length==0)
                throw new UnsupportedEncodingException();
            return new String(bytes, "UTF-8");
        } catch (Throwable t) {
            throw sneakyThrow(t);
            //throw SneakyThrowsTest.<RuntimeException>sneakyThrow0(t);
        }
    }

    public static RuntimeException sneakyThrow(Throwable t) {
        if (t == null) {
            throw new NullPointerException("t");
        } else {
            return SneakyThrowsTest.<RuntimeException>sneakyThrow0(t);
        }
    }

    /**
     * 显然魔法 藏在Lombok.sneakyThrow(t);中。可能大家都会以为这个方法就是new RuntimeException()之类的。
     * 然而事实并非如此。阅读代码可以看出整个方法其实最核心的逻辑是throw (T)t;，
     * 利用泛型将我们传入的Throwable强转为RuntimeException。
     * 虽然事实上我们不是RuntimeException。但是没关系。因为JVM并不关心这个。
     * 泛型最后存储为字节码时并没有泛型的信息。这样写只是为了骗过javac编译器
     */
    public static <T extends Throwable> T sneakyThrow0(Throwable t) throws T {
        throw (T) t;
    }

}
