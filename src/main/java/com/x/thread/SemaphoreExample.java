package com.x.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore 信号量，多线程运行控制器，控制统一时间并发的线程数量，最适用的场景就是限流
 */
public class SemaphoreExample {
    private static final int THREAD_SIZE = 30;
    /**
     * 同一时间，只有permits 数量的线程执行
     */
    private static final Semaphore S = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE * 2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    System.out.println("Semaphore out before:" + finalI + "  currentTimeMillis:" + System.currentTimeMillis());
                    S.acquire();
                    System.out.println(Thread.currentThread().getName() + "  currentTimeMillis: " + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("thread count: " + finalI);
                    S.release();
                    System.out.println("Semaphore out after: " + finalI + "   currentTimeMillis: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // sonar 让加上
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
    }
}
