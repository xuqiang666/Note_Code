package com.y.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * //https://blog.csdn.net/weijiaxiaobao/article/details/51217265
 */
public class MutiThreadException {

    public static void main(String[] args) {
        System.out.println("MutiThreadException.main()------>start<------");
        try {
            List<Future<Object>> resultList = new ArrayList<>();
            ExceptionHandle exceptionHandle = new ExceptionHandle();
            ExecutorService service = Executors.newFixedThreadPool(6);
            for (int i = 0; i < 3; i++) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("id " + Thread.currentThread().getName() + "---start>");
                        if (Thread.currentThread().getName().endsWith("2")) {
//                          int i=1/0;
                            throw new RuntimeException();
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("id " + Thread.currentThread().getName() + "----end:");
                    }
                });
                //不是用线程池执行线程，使用循环的方式创建多线程，那么就用下面这两行和ExceptionHandle类来捕获子线程中的异常
//              t.setUncaughtExceptionHandler(new ExceptionHandle());
//                t.start();
                //使用线程池的方式执行线程，如果子线程有异常的话，ExecutorService就已经捕获到了，我们可以从future对象中获取异常。正常情况下future对象中获取的是null，有异常的话获取到的就是异常信息
                Future<Object> future = (Future<Object>) service.submit(t);
                resultList.add(future);
            }
            for (Future<Object> f : resultList) {
                //在这里遍历就可以获取到future里面的异常，如果获取到的是null就说明成功执行了，没有异常。这里使用future的get方法时必须使用try catch才能获取到future对象里面的异常
                try {
                    Object o = f.get();
                    System.out.println(o);
                } catch (Exception e) {
                    System.out.println(e.toString() + "===========================");
                }
            }
            service.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("MutiThreadException.main()------>end<------");
    }

    public static class ExceptionHandle implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("子线程的异常，通过主线程捕获到了");
        }
    }
}
