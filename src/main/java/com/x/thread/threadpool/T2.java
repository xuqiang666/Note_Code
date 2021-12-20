package com.x.thread.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 测试拒绝策略
 */
@Slf4j
public class T2 {
    public static void main(String[] args) throws Exception {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        ThreadFactory threadFactory = new ThreadFactory() {
            //  int i = 0;  用并发安全的包装类
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
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory, handler);
        for (int i = 0; i < 100; i++) {
            try {
                executor.execute(new Thread(() -> log.info(Thread.currentThread().getName() + " work")));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        executor.shutdown();
    }
}
