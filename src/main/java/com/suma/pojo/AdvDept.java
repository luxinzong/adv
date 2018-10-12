package com.suma.pojo;

import lombok.Data;

@Data
public class AdvDept extends BasePojo{
    private Integer deptId;

    private Integer parentId;

    private String ancestors;

    private String deptName;

    private Integer orderNum;

    private String leader;

    private String phoneNumber;

    private String status;

}