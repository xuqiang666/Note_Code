package com.xqz.test;

/**
 * 测试匿名内部类和lambda表达式的this“指针”
 * 结论：
 * lambda表达式虽然也会构建一个类，但是内部却没有自己的this指针，
 * 当在内部使用this指针时，代指的是表达式外部对应的对象
 */
public class LambdaTest {
    public static void main(String[] args) {
        new LambdaTest().test1();
    }

    void test1() {
        System.out.println("Test对象hashCode=" + this.hashCode());
        System.out.println("------------------------------");
        f(() -> {
            System.out.println("lambda内的this:" + this.hashCode());
        });
        System.out.println("--------------");
        f(new Inter1() {
            @Override
            public void func() {
                System.out.println("内部类内的this:" + this.hashCode());
                System.out.println("内部类内的LambdaTest.this:" + LambdaTest.this.hashCode());
            }
        });
    }

    private static void f(Inter1 i) {
        i.func();
        System.out.println("i.hashCode=" + i.hashCode());
        System.out.println("i所属class=" + i.getClass());
    }

}

interface Inter1 {
    void func();
}
