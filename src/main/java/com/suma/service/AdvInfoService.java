package com.suma.service;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;

import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
public interface AdvInfoService extends BaseService<AdvInfo, AdvInfoExample, Long> {
    List<AdvInfo> selectAdvInfo(Map<String,Object> map);

    AdvInfo findById(Long id);
}
