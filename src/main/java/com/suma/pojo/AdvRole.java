package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvRole {
    private Integer roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

}