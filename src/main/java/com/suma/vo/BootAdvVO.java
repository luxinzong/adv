package com.suma.vo;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvRegion;
import lombok.Data;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/11/12 0012
 * @description
 */
@Data
public class BootAdvVO extends AdvInfo {

    /**
     * 开机广告请求，广告版本号
     */
    private Integer version;

    /**
     * 有效区域id
     */
    private List<Integer> regionId;

    /**
     * 所有区域
     */
    private List<AdvRegion> advRegions;

    /**
     * 区域名称
     */
    private List<String> regionNames;

    /**
     * 对应关系列表
     */
    private List<InfoMaterialVO> infoMaterialVOS;

    /**
     * 页面条数
     */
    private Integer pageSize;

    /**
     * 页码数
     */
    private Integer pageNum;
}
