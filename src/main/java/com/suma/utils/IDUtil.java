package com.suma.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @auther: luxinzong
 * @date: 2018/10/23 0023
 * @description
 */
public class IDUtil {

    private static AtomicLong count = new AtomicLong();

    /**
     * 以数字开头的Id自增
     * @param
     */
    public static long get() {
        count.incrementAndGet();
        return count.get();
    }
    public static void set(long start) {
        count.set(start);
    }



}
