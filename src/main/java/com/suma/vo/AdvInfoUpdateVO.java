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

    private Long advInfoId;//广告ID

    private String name;//广告名称

    private String start;//有效期起始日期

    private String end;//有效期终止日期

    private String periodTimeStart;//生效起始日期

    private String periodTimeEnd;//生效结束日期

    private Integer materialType;//素材类型

    private Integer status;//广告状态

    private Integer duration;//持续时间

    private String fileName;//资源文件名

    private Integer sequence;//播放顺序

}
