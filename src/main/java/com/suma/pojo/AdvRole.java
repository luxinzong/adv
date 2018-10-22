package com.suma.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class AdvRole extends BasePojo{
    private Integer roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    //保存角色对应菜单id
    private List<Integer> menuIds;
    //用户是否存在此角色标识,默认不保存
    private boolean flag = false;

}