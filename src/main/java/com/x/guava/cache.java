package com.x.guava;

import com.google.common.cache.*;
import com.xqz.domain.User;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class cache {

    private static final CacheLoader<Integer,User> userCacheLoader = new CacheLoader<Integer, User>() {
        @Override
        public User load(Integer integer) throws Exception {
            //模拟从数据库取出User
            User user = new User();
            user.setId(integer);
            user.setUsername(Thread.currentThread().getName()+"-"+
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"-"+integer);
            return user;
        }
    };

    private static final LoadingCache<Integer,User> userCacheDate = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .expireAfterWrite(Duration.ofMinutes(2))
            .refreshAfterWrite(Duration.ofMinutes(3))
            .maximumSize(10000L)        //缓存的最大容量（数量）
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())   //并发级别，根据CPU核心数
            .recordStats()          //开启缓存统计
            .build(userCacheLoader);

    private static final LoadingCache<Integer,User> userCacheDate2 = CacheBuilder.newBuilder()
            .maximumWeight(1024*1024*1024)      //指定最大容量
            .weigher((Weigher<Integer, User>) (k, v) -> k.toString().getBytes().length+v.toString().getBytes().length)
            .build(userCacheLoader);


    public static void main(String[] args) {
        Cache<String, String> build = CacheBuilder.newBuilder()
                .maximumWeight(1024 * 1024 * 1024) // 设置最大容量为 1M
                .weigher(new Weigher<String, String>() {
                    @Override
                    public int weigh(String key, String value) {
                        return key.getBytes().length + value.getBytes().length;
                    }
                }).build();

        CacheBuilder.newBuilder()
                .maximumWeight(1024*1024*1024)      //指定最大容量
                .weigher(new Weigher<String, String>() {
                    @Override
                    public int weigh(String s, String s2) {
                        return 0;
                    }
                })
                .build();
    }
}
