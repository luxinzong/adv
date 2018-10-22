package com.suma.pojo;


import lombok.Data;

@Data
public class InfoMaterial {
    private Long advInfoId; //广告信息ID

    private Long materialId;//素材ID

    private Integer sequence;//顺序

    private Integer duration;//持续时间
}