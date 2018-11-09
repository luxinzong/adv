package com.suma.vo;

import com.suma.pojo.*;
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

    private AdvLocation advLocation;//广告位

    private List<Integer> regionIds;//有效区域id

    private List<AdvRegion> advRegions;//所有区域

    private List<String> regionNames;//区域名称

    private List<String> serviceGroupNames;//频道分组名称

    private List<Long> serviceGroupIds;//频道分组Id

    private Integer type;//直播0点播1,不区分直播点播2

    private List<InfoMaterialVO> infoMaterialVOS;//对应关系列表

    private List<AdvFlyWord> advFlyWords;//字幕广告列表
}
