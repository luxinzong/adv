package com.suma.service.impl;

import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.dao.BaseDAO;
import com.suma.pojo.AdvInfoServiceGroup;
import com.suma.pojo.AdvInfoServiceGroupExample;
import com.suma.service.AdvInfoServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/29 0029
 * @description
 */
@Service
public class AdvInfoServiceGroupServiceImpl extends BaseServiceImpl<AdvInfoServiceGroup, AdvInfoServiceGroupExample, Long> implements AdvInfoServiceGroupService {
    @Resource
    public void setBaseDao(AdvInfoServiceGroupMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
