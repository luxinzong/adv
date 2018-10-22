package com.suma.service.impl;

import com.suma.dao.ServiceInfoMapper;
import com.suma.pojo.ServiceInfo;
import com.suma.pojo.ServiceInfoExample;
import com.suma.service.ServiceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/17
 * @description:
 */
@Service
public class ServiceInfoServiceImpl extends BaseServiceImpl<ServiceInfo, ServiceInfoExample, Long> implements ServiceInfoService {
    @Resource
    public void setBaseDao(ServiceInfoMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }
}
