package com.suma.vo;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvRegion;
import lombok.Data;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/11/8 0008
 * @description
 */
@Data
public class MenuAdvVO extends AdvInfo {

    private AdvLocation advLocation;//广告位

    private List<Integer> regionId;//有效区域id

    private List<InfoMaterialVO> infoMaterialVOS;//对应关系列表
}
