package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvRegion {
    private Integer regionId;

    private Integer parentId;

    private String ancestors;

    private String regionName;

    private Integer orderNum;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


}