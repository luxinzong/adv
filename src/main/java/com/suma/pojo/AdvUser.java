package com.suma.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class AdvUser extends BasePojo{

    private Integer userId;

    private Integer deptId;

    private String userName;

    private String password;

    private String phoneNumber;

    private String salt;

    private String status;

    private String loginIp;

    private Date loginDate;

    private List<Integer> roleIds;


}