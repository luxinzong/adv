package com.suma.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: luxinzong
 * @date: 2018/10/16
 * @description 页面字符串日期格式转换
 */
public class DateUtil {

    /**
     * 日期格式转换
     * @param str 格式2018-12-12
     * @return
     * @throws ParseException
     */
    public static Date dateFormat(String str) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  dateFormat.parse(str);
    }

    /**
     * 日期格式转换
     * @param str 格式12:12:12
     * @return
     * @throws ParseException
     */
    public static Date dateFormat2(String str) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return  dateFormat.parse(str);
    }

}
