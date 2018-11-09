package com.suma.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/16
 * @description:
 */
public class CommonUtils {

    public static final Map<String,List<String>> permMap = Maps.newConcurrentMap();

    /**
     * 应前端要求，添加对应导航对应缓存
     */
    static {
        permMap.put("广告管理",Lists.newArrayList("2",null));
        permMap.put("频道管理",Lists.newArrayList("3",null));
        permMap.put("资源管理",Lists.newArrayList("4",null));
        permMap.put("审核管理",Lists.newArrayList("5",null));
        permMap.put("权限管理",Lists.newArrayList("6",null));
        permMap.put("系统管理",Lists.newArrayList("7",null));
        permMap.put("区域管理",Lists.newArrayList("8",null));
        permMap.put("广告编辑",Lists.newArrayList(null,"ADeditor"));
        permMap.put("频道列表",Lists.newArrayList(null,"channelList"));
        permMap.put("频道查询",Lists.newArrayList(null,"channelQuery"));
        permMap.put("图片管理",Lists.newArrayList(null,"img"));
        permMap.put("视频管理",Lists.newArrayList(null,"video"));
        permMap.put("文字管理",Lists.newArrayList(null,"text"));
        permMap.put("广告位管理",Lists.newArrayList(null,"ADSpace"));
        permMap.put("用户管理",Lists.newArrayList(null,"userList"));
        permMap.put("菜单管理",Lists.newArrayList(null,"menuTable"));
        permMap.put("角色管理",Lists.newArrayList(null,"roleManage"));
        permMap.put("部门管理",Lists.newArrayList(null,"department"));
        permMap.put("待审核列表",Lists.newArrayList(null,"ReviewList"));
        permMap.put("区域列表",Lists.newArrayList(null,"area"));

    }

    public static String getFileSaveName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return formatter.format(localDateTime)+ (int) (Math.random() * 10000);
    }
}
