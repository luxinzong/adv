package com.suma.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.List;


/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 部门树结构工具类
 **/
public class AncestorUtil<T> {

    public static final String SEPARTOR = ",";

    public static final String ROOT = "0";

    public static String calculateAncestor(String parentAncestor,int parentId){
        //如果祖先节点为null直接返回0
        if(StringUtils.isBlank(parentAncestor)){
            return ROOT;
        }else{
            return StringUtils.join(parentAncestor,SEPARTOR,parentId);
        }
    }



}
