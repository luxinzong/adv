package com.suma.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 前台添加部门信息对应VO
 **/
@Data
public class AdvDeptVO {
    //上级部门ID，不填默认为0
    private Integer parentId;
    //部门名称
    @NotBlank
    private String deptName;
    //显示顺序
    private Integer orderNum;
    //联系人
    private String leader;
    //电话
    private String phoneNumber;
}
