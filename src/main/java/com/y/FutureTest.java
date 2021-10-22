package com.y;

import com.mysql.jdbc.Connection;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {
    @Test
    public void sadadas() {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            // 等待一下，保证线程执行完成
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            // 这里输出为 true
            System.out.println(future.isDone());
            // 然后抛出异常
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }


    /**
     *  假设有一个带key的链接池，当key存在时，即直接返回key对应的对象；当key不存在时，则建立链接
     */
    private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();

    public Connection getConnection(String key) throws Exception {
        FutureTask<Connection> connectionTask = connectionPool.get(key);
        if (connectionTask != null) {
            return connectionTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    // TODO Auto-generated method stub
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            connectionTask = connectionPool.putIfAbsent(key, newTask);
            if (connectionTask == null) {
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }

    //建立Connection
    private Connection createConnection() {
        return null;
    }
}
