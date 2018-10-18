package com.suma.vo;


import lombok.Data;

import java.util.Date;


/**
 * @auther: luxinzong
 * @date: 2018/10/17 0017
 * @description
 */
@Data
public class AdvInfoQueryVO {

    private String name;//广告名称

    private Date start;//广告起始日期

    private Date end;//广告终止日期

    private Integer status;//广告状态

    private Integer pageSize;//页面条数

    private Integer pageNum;//页码数


}
