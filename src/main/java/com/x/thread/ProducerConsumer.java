package com.x.thread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 利用 wait 和 notify 实现生产消费模式
 */
public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        final int maxSize = 3;
        final LinkedList<String> queue = new LinkedList<String>();

        final Thread producer = new Thread("producer-thread") {
            @Override
            public void run() {
                while(true) {
                    synchronized (queue) {
                        while (queue.size() == maxSize) {
                            try {
                                System.out.println(Thread.currentThread().getName() + "因队列满容挂起自己,让 consumer 开始执行");
                                queue.wait();
                                System.out.println(Thread.currentThread().getName() + "被唤醒，往队列中添加元素");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int index = queue.size();
                        queue.add("task-" + index);
                        System.out.println(Thread.currentThread().getName() + "添加任务 task-" + index);
                        queue.notifyAll();
                    }
                }
            }
        };

        Thread consumer = new Thread("consumer-thread") {
            @Override
            public void run() {
                while (true) {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + "因队列为空挂起自己，让 producer 开始执行");
                            try {
                                queue.wait();
                                System.out.println(Thread.currentThread().getName() + "被唤醒，消费队列中的元素");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "执行任务" + queue.poll());
                        queue.notifyAll();
                    }
                }
            }
        };

        producer.start();
        TimeUnit.MILLISECONDS.sleep(1); // 让 producer 先启动
        consumer.start();

    }

}

