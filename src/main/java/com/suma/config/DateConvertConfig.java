package com.suma.config;

import org.springframework.core.convert.converter.Converter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/16 0016
 * @description
 */

public class DateConvertConfig implements Converter<String, Date> {

    private static final List<String> formats = new ArrayList<>(2);
    static {
        formats.add("yyyy-MM-dd");
        formats.add("HH:mm:ss");
    }
    @Override

    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formats.get(0));
        } else if (source.matches("^\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formats.get(2));
        } else {
            throw new IllegalArgumentException("日期格式不对");
        }
    }

    /**
     * 格式化日期
     * @param dateStr 字符日期
     * @param format 格式
     * @return
     */
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}













