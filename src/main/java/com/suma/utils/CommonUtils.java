package com.suma.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/16
 * @description:
 */
public class CommonUtils {
    public static String getFileSaveName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(localDateTime)+ (int) (Math.random() * 10000);
    }
}
