package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/18 0018
 * @description
 */
@Data
public class AdvInfoVO {

    private Long id;//广告ID

    private Long advTypeId;//广告类型

    private String advType;//广告类型

    private String advTypeName;//广告类型名称

    private String name;//广告名称

    private String startDate;//起始日期

    private String endDate;//终止日期

    private String periodTimeStart;//有效期生效日期

    private String periodTimeEnd;//有效期结束日期

    private Integer status;//广告状态

    private Integer pageSize;//页面条数

    private Integer pageNum;//页码数

    private Integer materialType;//素材类型

    private List<InfoMaterialVO> infoMaterialVOS;//对应关系列表

    private List<AdvFlyWord> advFlyWords;//字幕广告列表
}
