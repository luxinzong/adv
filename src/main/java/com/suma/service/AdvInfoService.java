package com.suma.service;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
public interface AdvInfoService extends BaseService<AdvInfo, AdvInfoExample, Long> {
    AdvInfo selectAdvInfo(String name,String startDate,String endDate,Integer status,Long advTypeId);

    AdvInfo findById(Long id);
}
