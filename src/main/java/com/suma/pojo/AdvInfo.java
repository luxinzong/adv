package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvInfo{
    private Long id;

    private Long advLocationId;

    private Long advTypeId;

    private String name;

    private Integer materialType;

    private Integer status;

    private String startDate;

    private String endDate;

    private String periodTimeStart;

    private String periodTimeEnd;

    private String checkUser;

    private String checkNote;

    private Date checkTime;

    private String createdUser;

    private Date createdTime;

    private String lastEditTime;

    private String lastEditUser;

    private String lastEditModule;

    private String reservedString;

    private Long reservedInt;

}