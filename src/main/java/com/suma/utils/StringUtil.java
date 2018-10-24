package com.suma.utils;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/24 0024
 * @description
 */
public class StringUtil {

    /**
     * 将str 纯数字字符串转换成 数字集合list
     * @param str
     * @return list<Long></>
     */
    public static List<Long> convertstr(String str) {
        String[] strs = str.split(",");
        Long[] ids = (Long[]) ConvertUtils.convert(strs, Long.class);
        return Arrays.asList(ids);
    }

}
