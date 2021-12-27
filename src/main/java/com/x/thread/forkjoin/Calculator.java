package com.x.thread.forkjoin;

/**
 * @author fangshixiang@vipkid.com.cn
 * @description //
 * @date 2018/11/5 14:26
 */
public interface Calculator {

    /**
     * 把传进来的所有numbers 做求和处理
     *
     * @param numbers
     * @return 总和
     */
    long sumUp(long[] numbers);
}

