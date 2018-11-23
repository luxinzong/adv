package com.suma.vo;

import lombok.Data;

/**
 * @author: luxinzong
 * @date: 2018/11/19 0019
 * @description 广告播发前端对象VO
 */
@Data
public class AdvPutVO {

    private String startDate;

    private String endDate;
    private Integer regionId;
    private Long advTypeId;
    private Integer status;
    private Integer version;
    private Long serviceId;
    private Integer type;
    private String card;
    private Integer pageSize;
    private Integer pageNum;
}
