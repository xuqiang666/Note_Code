package com.x.thread.threadpool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试 线程池不同的拒绝策略产生的效果
 * 1. 队列长度 90 + AbortPolicy ；主线程报错：拒绝，无法执行shutdown
 * 2. 队列长度 90 + AbortPolicy ； 在get处的catch到超时
 */
public class MyTask implements Callable<Integer> {

    private int num;

    public MyTask(int i) {
        this.num = i;
    }

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(90);
        ThreadFactory threadFactory = new ThreadFactory() {
            final AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                //创建线程 把任务传进来
                Thread thread = new Thread(r);
                // 给线程起个名字
                thread.setName("MyThread" + atomicInteger.getAndIncrement());
                return thread;
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, new RejectedPolicyWithReport());
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        ArrayList<Future<Integer>> myFutures = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            MyTask task = new MyTask(i);
            Future<Integer> future = executor.submit(task);
            myFutures.add(future);
        }

        // 当队列90，拒绝策略选择的是AbortPolicy 才能正确执行到此处，size为100
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + myFutures.size());
        int te = 0;
        for (Future<Integer> future : myFutures) {
            Integer n = 9999;
            try {
                // 如果在这里不设置超时时间，当多余的任务被拒绝后，主线程将持续阻塞在get方法
                n = future.get(1L, TimeUnit.SECONDS);
            } catch (Exception e) {
                if (e instanceof TimeoutException) {
                    te++;
                    System.out.println(te + " 超时异常-----------" + e.getMessage());
                } else {
                    System.out.println("--得到异常啦啦啦--" + e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("=========输出结果 " + n);
        }
        executor.shutdown();
        //System.in.read(); //阻塞主线程
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(100);
        int s = num;
        if (num == 50) {
            int a = num / 0;
        }
        System.out.println(Thread.currentThread().getName() + "线程执行：  task-【" + num + "】成功执行ing。。。");
        return s;
    }
}
