package com.suma.service.impl;

import com.suma.dao.AdvLocationMapper;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvLocationExample;
import com.suma.service.AdvLocationService;
import com.suma.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/12
 * @description:
 */
@Service
public class AdvLocationServiceImpl extends BaseServiceImpl<AdvLocation, AdvLocationExample, Long> implements AdvLocationService {

    @Resource
    public void setBaseDao(AdvLocationMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }
}
