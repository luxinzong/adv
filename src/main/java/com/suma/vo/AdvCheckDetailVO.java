package com.suma.vo;

import lombok.Data;

import java.util.Date;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
@Data
public class AdvCheckDetailVO {

    private Long advLocationId;//广告位ID

    private Long advTypeId;// 广告类型ID

    private String name;//广告名称

    private Integer materialType;//素材类型

    private Integer status;//广告状态

    private Date startDate;//起始日期

    private Date endDate;//终止日期

    private Date periodTimeStart;//有效期生效日期

    private Date periodTimeEnd;//有效期结束日期

    private String checkUser;//审核人

    private String checkNote;//审核意见

    private Date checkTime;//审核时间

    private String createdUser;//创建用户

    private Date createdTime;//创建时间

    private Date lastEditTime;//编辑时间

    private String lastEditUser;//编辑用户

    private String lastEditModule;//编辑模块

    private Integer region;//区域码

    private String mark;//备注

    private Long xPosition;//横坐标

    private Long yPosition;//纵坐标

    private Long mWidth;//宽度

    private Long mHeight;//高度

    private String content;//文字内容

    private Long displayTimes;//显示次数

    private Long interval;//时间间隔

    private String fontColour;//字体颜色

    private String backgroundColour;//字幕背景色

    private Long speed;//跑马灯速度

    private Long direct;//方向

    private Long duration;//持续时间

}
