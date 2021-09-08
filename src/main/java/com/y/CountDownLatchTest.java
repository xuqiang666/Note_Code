package com.y;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用 countdownlatch 模拟高并发
 */
public class CountDownLatchTest {

    /*
    等待100次countdown，执行await
     */
    private static final CountDownLatch CD = new CountDownLatch(100);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        CD.await();
                        // 消费
                        System.out.println(Thread.currentThread().getName() + " 执行成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            CD.countDown();
        }
    }
}
