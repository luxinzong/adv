package com.suma.utils;

import com.suma.pojo.AdvInfo;

import java.util.Date;

/**
 * @author: luxinzong
 * @date: 2018/11/13 0013
 * @description 用于生成操作或编辑的用户和操作或编辑的时间以及操作模块
 */
public class UserAndTimeUtils {

    /**
     * 生成创建用户和创建时间
     * @return
     */
    public static AdvInfo setCreateUserAndTime(AdvInfo advInfo) {
        advInfo.setCreatedUser("ASD");//todo
        advInfo.setCreatedTime(new Date());
        advInfo.setLastEditUser("ASD");//todo
        return advInfo;
    }

    /**
     * 生成编辑用户和编辑模块
     * @return
     */
    public static AdvInfo setEditUserAndTime(AdvInfo advInfo) {
        advInfo.setLastEditModule("");
        advInfo.setLastEditUser("asda");
        return advInfo;
    }
}
