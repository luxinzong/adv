package com.suma.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdvUser {
    private Integer userId;

    private Integer deptId;

    private String userName;

    private String password;

    private String phoneNumber;

    private String salt;

    private String status;

    private String loginIp;

    private Date loginDate;

}