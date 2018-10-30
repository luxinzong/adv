package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import lombok.Data;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/18 0018
 * @description
 */
@Data
public class AdvInfoUpdateVO {

    private Long id;//广告ID

    private Long advTypeId;//广告类型ID

    private String name;//广告名称

    private String start;//有效期起始日期

    private String end;//有效期终止日期

    private String periodTimeStart;//生效起始日期

    private String periodTimeEnd;//生效结束日期

    private Integer materialType;//素材类型

    private Integer status;//广告状态

    private List<InfoMaterialVO> infoMaterialVOS;//广告资源列表

    private List<AdvFlyWord> advFlyWords;//字幕信息

}
