package com.suma.service.impl;

import com.suma.dao.AdvGathererMapper;
import com.suma.dao.BaseDAO;
import com.suma.pojo.AdvGatherer;
import com.suma.pojo.AdvGathererExample;
import com.suma.service.AdvGathererService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: luxinzong
 * @date: 2018/11/21 0021
 * @description
 */
@Service
public class AdvGathererServiceImpl extends BaseServiceImpl<AdvGatherer, AdvGathererExample, Long>
        implements AdvGathererService {
    @Resource
    public void setBaseDao(AdvGathererMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
