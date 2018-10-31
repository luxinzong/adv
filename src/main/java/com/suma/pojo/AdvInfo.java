package com.suma.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;


@Data
public class AdvInfo {
    private Long id;

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

    private String region;//区域码

    private String reservedString;

    private Long reservedInt;

}