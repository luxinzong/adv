package com.suma.service.impl;

import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.dao.ServiceInfoMapper;
import com.suma.pojo.ServiceGroupExample;
import com.suma.pojo.ServiceInfo;
import com.suma.pojo.ServiceInfoGroup;
import com.suma.pojo.ServiceInfoGroupExample;
import com.suma.service.BaseService;
import com.suma.service.ServiceInfoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@Service
public class ServiceInfoGroupServiceImpl extends BaseServiceImpl<ServiceInfoGroup, ServiceInfoGroupExample, Long> implements ServiceInfoGroupService {
    @Resource
    public void setBaseDao(ServiceInfoGroupMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private ServiceInfoGroupMapper serviceInfoGroupMapper;
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    @Override
    public List<String> findServicesByGroupId(Long sgId) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSgidEqualTo(sgId);
        List<ServiceInfoGroup> serviceInfoGroups = serviceInfoGroupMapper.selectByExample(example);
        List<String> list = new ArrayList<>();
        if (serviceInfoGroups != null) {
            for (ServiceInfoGroup serviceInfoGroup : serviceInfoGroups) {
                ServiceInfo serviceInfo = serviceInfoMapper.selectByPrimaryKey(serviceInfoGroup.getSid());
                list.add(serviceInfo.getServiceName());
            }
        }
        return list;
    }

    @Override
    public void deleteByGroupId(Long sgid) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSgidEqualTo(sgid);
        serviceInfoGroupMapper.deleteByExample(example);
    }
}
