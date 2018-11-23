package com.suma.utils;

import com.google.common.collect.Lists;
import com.suma.pojo.AdvInfo;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.ArrayList;
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

    public static List<Integer> str2Int(String str) {
        String[] strings = str.split(",");
        Integer[] ids = (Integer[]) ConvertUtils.convert(strings, Integer.class);
        return Arrays.asList(ids);
    }

    /**
     * 将特定字符串转换成Integer集合，
     * @param string “1，2，3” “1”
     * @return List<Integers></>
     */
    public static List<Integer> getRegionId(String string) {
        List<Integer> ids = new ArrayList<>();
        if (string.indexOf(",") == -1) {
            ids.add(Integer.valueOf(string));
            return ids;
        } else {
            String[] strs = string.split(",");
            Integer[] a = (Integer[]) ConvertUtils.convert(strs, Integer.class);
            return Arrays.asList(a);
        }
    }


}
