package com.suma.vo;

import com.suma.pojo.AdvMaterial;
import lombok.Data;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/18 0018
 * @description
 */
@Data
public class AdvInfoInsertVO {

    private Long advTypeId;//广告类型

    private String name;//广告名称

    private String start;//起始日期

    private String end;//终止日期

    private String periodTimeStart;//有效期生效日期

    private String periodTimeEnd;//有效期结束日期

    private Integer status;//广告状态

    private Integer materialType;//素材类型

    private List<InfoMaterialVO> infoMaterialVOS;//对应关系列表
}
