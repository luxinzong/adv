package com.suma.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class AdvUser extends BasePojo{

    //用户id
    private Integer userId;
    //部门id
    private Integer deptId;
    //部门名称
    private String userName;
    //密码
    private String password;
    //联系电话
    private String phoneNumber;
    //盐值
    private String salt;
    //状态
    private String status;
    //登录ip
    private String loginIp;
    //登录时间
    private Date loginDate;
    //对应角色id
    private List<Integer> roleIds;



}