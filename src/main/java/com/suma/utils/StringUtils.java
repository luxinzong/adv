package com.suma.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luxinzong
 * @date 2018/10/13
 */
public class StringUtils {

    @Autowired
    Date date;

    public Date StrToDate(String string) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
