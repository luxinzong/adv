package com.suma.service.impl;

import com.suma.dao.AdvInfoMapper;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.service.AdvInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class AdvInfoServiceImpl extends BaseServiceImpl<AdvInfo, AdvInfoExample, Long> implements AdvInfoService {

    @Resource
    public void setBaseDao(AdvInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
