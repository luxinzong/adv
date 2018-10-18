package com.suma.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/11 0011
 * @Description 部门对象POJO类
 **/
@Data
public class AdvDept extends BasePojo{
    //序列化ID
    private static final long serialVersionUID = 1L;
    //部门ID
    @NotNull
    private Integer deptId;
    //父部门ID
    private Integer parentId;
    //祖级列表
    private String ancestors;
    //部门名称
    @NotBlank
    private String deptName;
    //显示顺序
    private Integer orderNum;
    //负责人
    private String leader;
    //电话
    private String phoneNumber;
    //部门状态：0正常,1停用
    @NotBlank
    private String status;

}