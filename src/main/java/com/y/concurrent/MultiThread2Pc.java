package com.y.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * https://mp.weixin.qq.com/s?__biz=Mzg3NjU3NTkwMQ==&mid=2247505178&idx=1&sn=099ab43088d40b73dca67dd11f210536&source=41#wechat_redirect
 * 首先假设我们有一个全局变量为 Boolean 类型，默认为true，含义为可以提交事务。
 * <p>
 * 然后我们开启 5 个子线程，各自处理 10w 条数据。
 * <p>
 * 在子线程里面，我们可以先通过编程式事务开启事务，插入 10w 条数据后不进行提交。同时告诉主线程，我这边准备好了，进入等待。
 * <p>
 * 如果子线程里面出现了异常，那么我就告诉主线程，我这边出问题了，然后自己进行回滚。
 * <p>
 * 不论怎样，主线程都会收集到 5 个子线程的状态。主线程检测到，如果有一个线程出现了问题，那么设置全局变量为 false，含义为回滚事务。
 * <p>
 * 然后唤醒所有等待的子线程，进行回滚。
 */
public class MultiThread2Pc {
    /**
     * 是否可以提交
     */
    public static volatile boolean IS_OK = true;

    public static void main(String[] args) {
        //子线程等待主线程通知
        CountDownLatch mainMonitor = new CountDownLatch(1);
        int threadCount = 5;
        CountDownLatch childMonitor = new CountDownLatch(threadCount);
        //子线程运行结果
        List<Boolean> childResponse = new ArrayList<Boolean>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "：开始执行");
                    if (finalI == 4) {
                        throw new Exception("出现异常");
                    }
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                    childResponse.add(Boolean.TRUE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：准备就绪,等待其他线程结果,判断是否事务提交");
                    mainMonitor.await();
                    if (IS_OK) {
                        System.out.println(Thread.currentThread().getName() + "：事务提交");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "：事务回滚");
                    }
                } catch (Exception e) {
                    childResponse.add(Boolean.FALSE);
                    childMonitor.countDown();
                    System.out.println(Thread.currentThread().getName() + "：回滚自己");
                    System.out.println(Thread.currentThread().getName() + "：出现异常,开始事务回滚");
                }
            });
        }
        //主线程等待所有子线程执行response
        try {
            childMonitor.await();
            for (Boolean resp : childResponse) {
                if (!resp) {
                    //如果有一个子线程执行失败了，则改变mainResult，让所有子线程回滚
                    System.out.println(Thread.currentThread().getName() + ":有线程执行失败，标志位设置为false");
                    IS_OK = false;
                    break;
                }
            }
            //主线程获取结果成功，让子线程开始根据主线程的结果执行（提交或回滚）
            mainMonitor.countDown();
            //为了让主线程阻塞，让子线程执行。
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
