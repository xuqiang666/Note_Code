package com.x.stream.recursivetask;

import org.junit.Test;

import java.util.stream.LongStream;

/**
 * @Author: 许庆之 on 2020/12/4.
 * 并行流只有在涉及大量数据计算的时候才适合使用
 * 数据量小，计算简单的场景下不适合使用 并行流
 */
public class ParallelStream {

    /**
     * 运行结果不正确，且每次计算的结果都不一样，因为summer.add方法线程不安全
     */
    @Test
    public void test01() {
        Summer summer = new Summer();
        LongStream.rangeClosed(1, 100000000)
                .parallel()
                .forEach(summer::add);
        System.out.println("result：" + summer.sum);
    }

    static class Summer {
        public long sum = 0;

        public void add(long value) {
            //这个操作是非原子的，会出现线程安全问题
            sum += value;
        }
    }


    /**
     * 正确的使用方式，线程安全，计算出来的sum不会因为并发而错误
     * Long::sum 计算，reduce 合并，parallelStream的Spliterator接口实现的拆分
     */
    @Test
    public void test02() {
        long result = LongStream.rangeClosed(1, 100000000)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println("result：" + result);
    }
}
