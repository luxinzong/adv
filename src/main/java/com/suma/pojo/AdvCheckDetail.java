package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvCheckDetail {

    private Long id;//审核详情ID

    private Long advInfoId;//关联广告ID

    private Integer region;//区域码

    private String checkUser;//审核人

    private Integer status;//广告状态

    private String mark;//备注

    private Date checkTime;//审核时间

}