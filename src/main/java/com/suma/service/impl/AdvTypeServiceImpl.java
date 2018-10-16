package com.suma.service.impl;

import com.suma.dao.AdvTypeMapper;
import com.suma.pojo.AdvType;
import com.suma.pojo.AdvTypeExample;
import com.suma.service.AdvTypeService;
import com.suma.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
@Service
public class AdvTypeServiceImpl extends BaseServiceImpl<AdvType, AdvTypeExample, Long> implements AdvTypeService {
    @Resource
    public void setBaseDao(AdvTypeMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }
}
