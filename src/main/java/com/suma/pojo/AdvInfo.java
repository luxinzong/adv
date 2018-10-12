package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvInfo {

    private Long id;//广告信息ID

    private Long advLocationId;//广告位置信息ID

    private Long advTypeId;//广告位ID

    private String name;//广告名称

    private Integer materialType;//广告资源类型

    private Integer status;//广告状态

    private Date startDate;//广告有效期起始日期

    private Date endDate;//广告有效期终止日期

    private Date periodTimeStart;//生效时间段开始时间

    private Date periodTimeEnd;//生效时间段结束时间

    private String checkUser;//审核人

    private String checkNote;//审核意见

    private Date checkTime;//审核时间

    private String createdUser;//广告创建人

    private Date createdTime;//广告创建时间

    private Date lastEditTime;//最近编辑时间

    private String lastEditUser;//最近编辑人

    private String lastEditModule;//最近编辑模块

    private Integer region;//所述区域

    private String reservedString;//预留字段

    private Long reservedInt;//预留字段

   }