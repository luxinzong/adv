package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvLocation;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
@Data
public class AdvCheckDetailVO {

    private Long id;//广告ID

    private String advType;//广告类型

    private String advTypeName;//广告类型名称

    private Long advLocationId;//广告位ID

    private Long advTypeId;// 广告类型ID

    private String name;//广告名称

    private Integer materialType;//素材类型

    private Integer status;//广告状态

    private String startDate;//起始日期

    private String endDate;//终止日期

    private String periodTimeStart;//有效期生效日期

    private String periodTimeEnd;//有效期结束日期

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

    private Long serviceGroupId;//频道分组信息

    private List<String> regionNames;//区域信息

    private String serviceGroupName;//频道名称

    private AdvLocation advLocation;//广告位置信息

    private List<AdvFlyWord> advFlyWords;//字幕广告信息

}
