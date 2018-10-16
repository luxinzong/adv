package com.suma.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


@Data
public class AdvInfo {
    private Long id;

    private Long advLocationId;//广告位ID

    private Long advTypeId;// 广告类型ID

    private String name;//广告名称

    private Integer materialType;//素材类型

    private Integer status;//广告状态

    private Date startDate;//起始日期

    private Date endDate;//终止日期

    private Time periodTimeStart;//有效期生效日期

    private Time periodTimeEnd;//有效期结束日期

    private String checkUser;

    private String checkNote;

    private Date checkTime;

    private String createdUser;

    private Date createdTime;

    private Timestamp lastEditTime;

    private String lastEditUser;

    private String lastEditModule;

    private Integer region;

    private String reservedString;

    private Long reservedInt;

}