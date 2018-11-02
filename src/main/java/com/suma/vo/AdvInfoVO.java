package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvRegion;
import com.suma.pojo.ServiceGroup;
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
public class AdvInfoVO extends AdvInfo {

    private Integer pageSize;//页面条数

    private Integer pageNum;//页码数

    private List<AdvRegion> advRegions;//所有区域

    private List<String> regionNames;//区域名称

    private List<ServiceGroup> serviceGroups;//频道分组

    private List<InfoMaterialVO> infoMaterialVOS;//对应关系列表

    private List<AdvFlyWord> advFlyWords;//字幕广告列表
}
