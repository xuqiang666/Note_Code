package com.x.stream.recursivetask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Author: 许庆之 on 2020/12/4.
 * Java7中提供了一个处理大数据的fork/join框架，屏蔽掉了线程之间交互的处理，更加专注于数据的处理。
 * https://juejin.cn/post/6900711829404647431
 * <p>
 * 使用Fork/Join框架首先需要创建自己的任务，需要继承RecursiveTask，实现抽象方法 compute
 * 实现类需要在该方法中实现任务的拆分、计算、合并；伪代码可以表示成这样：
 * if(任务已经不可拆分){
 * return 顺序计算结果;
 * } else {
 * 1.任务拆分成两个子任务
 * 2.递归调用本方法，拆分子任务
 * 3.等待子任务执行完成
 * 4.合并子任务的结果
 * }
 */
public class SumRecursiveTask extends RecursiveTask<Long> {
    private long[] numbers;
    private int start;
    private int end;

    public SumRecursiveTask(long[] numbers) {
        this.numbers = numbers;
        this.start = 0;
        this.end = numbers.length;
    }

    public SumRecursiveTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        //小于20000个就不在进行拆分
        if (length < 20000) {
            return sum();
        }
        //进行任务拆分
        SumRecursiveTask leftTask = new SumRecursiveTask(numbers, start, start + length / 2);
        //进行任务拆分
        SumRecursiveTask rightTask = new SumRecursiveTask(numbers, start + (length / 2), end);
        //把该子任务交友ForkJoinPoll线程池去执行
        leftTask.fork();
        //把该子任务交友ForkJoinPoll线程池去执行
        rightTask.fork();
        //把子任务的结果相加
        return leftTask.join() + rightTask.join();
    }


    private long sum() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        /*LongStream.rangeClosed 按顺序产生从start到end包括end，间隔为1*/
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        Long result = new ForkJoinPool().invoke(new SumRecursiveTask(numbers));
        System.out.println("result：" + result);
    }

}

