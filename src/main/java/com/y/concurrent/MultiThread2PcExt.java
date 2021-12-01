package com.y.concurrent;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 多线程实现同时提交和回滚，本质是2PC的思想，但在提交失败时会导致数据不一致
 */
public class MultiThread2PcExt {

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
        List<Boolean> childResponse = new ArrayList<>();
        List<Future<Object>> resultList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "：开始执行");
//                        if (finalI == 4) {
//                            throw new Exception("出现异常");
//                        }
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                        childResponse.add(Boolean.TRUE);
                    } catch (Exception e) {
                        childResponse.add(Boolean.FALSE);
                        //System.out.println(Thread.currentThread().getName() + "：回滚自己");
                        System.out.println(Thread.currentThread().getName() + "：出现异常,开始事务回滚");
                    } finally {
                        childMonitor.countDown();
                    }

                    System.out.println(Thread.currentThread().getName() + "：准备就绪,等待其他线程结果,判断是否事务提交");
                    try {
                        mainMonitor.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (IS_OK) {
                        if (finalI == 3) {
                            throw new RuntimeException("提交时异常");
                        }
                        System.out.println(Thread.currentThread().getName() + "：事务提交");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "：事务回滚");
                    }
                }
            });
            //使用线程池的方式执行线程，如果子线程有异常的话，ExecutorService就已经捕获到了，我们可以从future对象中获取异常。
            // 正常情况下future对象中获取的是null，有异常的话获取到的就是异常信息
            Future<Object> future = (Future<Object>) executor.submit(t);
            resultList.add(future);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        //主线程获取结果成功，让子线程开始根据主线程的结果执行（提交或回滚）
        mainMonitor.countDown();
        //为了让主线程阻塞，让子线程执行。
        //Thread.currentThread().join();


        //拒绝接受新线程并等待处理完毕，超时时间为20秒
        executor.shutdown();
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        // 可以通过下面的方式，将子线程提交时，如果future返回的是null，代表提交正常，当get操作进入异常，代表提交操作出错了，需要手动介入进行处理
        // 如果线程池运行完毕的后续处理，拼装异常信息
        if (executor.isTerminated()) {
            for (Future future : resultList) {
                String errorString = null;
                try {
                    errorString = (String) future.get();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } catch (ExecutionException e) {
                    errorString = e.getMessage();
                }
                if (StringUtils.isNotEmpty(errorString)) {
                    sb.append(errorString);
                }
            }
        }
        //打印异常信息
        if (StringUtils.isNotEmpty(sb.toString())) {
            System.out.println(sb.toString());
            //throw new RuntimeException(sb.toString());
        } else {
            System.out.println("succeed!");
        }
    }

}
