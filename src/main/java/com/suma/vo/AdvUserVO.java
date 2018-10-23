package com.suma.vo;

import lombok.Data;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/
@Data
public class AdvUserVO {

    private String userName;

    private String password;

    private String phoneNumber;

    private List<Integer> roleIds;

    private Integer deptId;

}
