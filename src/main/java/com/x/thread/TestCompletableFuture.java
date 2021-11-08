package com.x.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * @Author: 许庆之 on 2020/12/2.
 */
public class TestCompletableFuture {
    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";
    private static final String commandstr03 = "hahah";
    private static final String commandstr04 = "hahah";

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(new MyThreadt444(commandstr02), executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            //抛出异常
            System.out.println("exception " + e);
            return "exception";
        });

        CompletableFuture.supplyAsync(new MyThreadt333(commandstr02), executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            System.out.println("exception " + e);
            return "exception";
        });
    }
}


class MyThreadt333 implements Supplier<String> {

    // 要运行的命令
    private String commandstr;

    public MyThreadt333(String commandstr) {
        this.commandstr = commandstr;
    }

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread333: " + i);
        }
        return String.valueOf(sum + 300000);
    }
}

class MyThreadt444 implements Supplier<String> {

    // 要运行的命令
    private String commandstr;

    public MyThreadt444(String commandstr) {
        this.commandstr = commandstr;
    }

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread444: " + i);
        }
        return String.valueOf(sum + 400000);
    }
}
