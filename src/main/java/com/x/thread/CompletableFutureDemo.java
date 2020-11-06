package com.x.thread;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 许庆之 on 2020/10/26.
 *  completableFuture ：增强的Future
 * @ https://blog.csdn.net/u011726984/article/details/79320004
 */
public class CompletableFutureDemo {

    /**
     * 可以在异步执行的各个阶段执行 传入的Function/Runnable/Consumer
     * */
    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        // 结果集
        List<String> list = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Integer> taskList = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calc(integer), executorService)
                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+LocalDate.now());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        // 对所有 Future调用join方法
        CompletableFuture.allOf(cfs).join();
        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
    }

    public int calc(Integer i) {
        try {
            if (i == 1) {
                //任务1耗时3秒
                Thread.sleep(3000);
            } else if (i == 5) {
                //任务5耗时5秒
                Thread.sleep(5000);
            } else {
                //其它任务耗时1秒
                Thread.sleep(1000);
            }
            System.out.println("task线程：" + Thread.currentThread().getName()
                    + ", 任务i=" + i + ",完成！ " + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**  allOf() 多个异步处理(针对有参返回)
     * https://www.cnblogs.com/funnyzpc/p/10801470.html
     * */
    @Test
    public void asyncThread3()throws Exception{
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "youth");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture all = CompletableFuture.allOf(a,b,c);
        all.get();

        String result = Stream.of(a, b,c)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    /** anyOf() 多个异步随机处理(针对有参返回)
     * */
    @Test
    public void asyncThread4()throws Exception{
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() ->{
            try{
                Thread.sleep(20);
                return "hello";
            }catch (Exception e){
                e.printStackTrace();
                return "none~";
            }
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "youth");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture<Object> any = CompletableFuture.anyOf(a,b,c);
        String result = (String)any.get();

        System.out.println(result);
    }
}
