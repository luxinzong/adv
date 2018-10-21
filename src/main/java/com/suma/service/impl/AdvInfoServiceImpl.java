package com.suma.service.impl;

import com.suma.dao.AdvInfoMapper;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.service.AdvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class AdvInfoServiceImpl extends BaseServiceImpl<AdvInfo, AdvInfoExample, Long> implements AdvInfoService {

    @Autowired
    AdvInfoMapper advInfoMapper;

    @Resource
    public void setBaseDao(AdvInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public AdvInfo selectAdvInfo(String name,String startDate,String endDate,Integer status,Long advTypeId) {
        return advInfoMapper.selectAdvInfo(name, startDate, endDate,status, advTypeId);
    }

    @Override
    public AdvInfo findById(Long id) {
        return advInfoMapper.findById(id);
    }
}
