package com.suma.vo;

import com.suma.pojo.InfoMaterial;
import lombok.Data;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/18 0018
 * @description
 */
@Data
public class AdvInfoUpdateVO {

    private Long id;

    private String name;

    private String start;

    private String end;

    private String periodTimeStart;

    private String periodTimeEnd;

    private Integer materialType;

    private Integer status;

}
