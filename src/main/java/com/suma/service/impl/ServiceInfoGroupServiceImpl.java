
package com.suma.service.impl;

import com.suma.dao.ChannelInfoMapper;
import com.suma.dao.ServiceGroupMapper;
import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.dao.ServiceInfoMapper;
import com.suma.pojo.*;
import com.suma.service.ServiceInfoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private ServiceGroupMapper serviceGroupMapper;
    @Autowired
    private ChannelInfoMapper channelInfoMapper;

    @Override
    public List<String> findServicesByGroupId(Long sgId) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSgidEqualTo(sgId);
        List<ServiceInfoGroup> serviceInfoGroups = serviceInfoGroupMapper.selectByExample(example);
        List<String> list = new ArrayList<>();
        if (serviceInfoGroups != null) {
            for (ServiceInfoGroup serviceInfoGroup : serviceInfoGroups) {
                ServiceInfo serviceInfo = serviceInfoMapper.selectByPrimaryKey(serviceInfoGroup.getSid());
                if (serviceInfo != null)
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

    @Override
    public List<ServiceGroup> findGroupBySId(Long id, Integer type) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSidEqualTo(id);
        List<ServiceInfoGroup> serviceInfoGroups = serviceInfoGroupMapper.selectByExample(example);

        List<ServiceGroup> serviceGroups = new ArrayList<>();
        for (ServiceInfoGroup serviceInfoGroup : serviceInfoGroups) {
            ServiceGroupExample serviceGroupExample = new ServiceGroupExample();
            serviceGroupExample.createCriteria().andSgidEqualTo(serviceInfoGroup.getSgid()).andTypeEqualTo(type);
            List<ServiceGroup> groups = serviceGroupMapper.selectByExample(serviceGroupExample);
            if (!CollectionUtils.isEmpty(groups)) {
                serviceGroups.add(groups.get(0));
            }

        }
        return serviceGroups;
    }

    @Override
    public List<String> findChannelIdsByGroupId(Long sgId) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSgidEqualTo(sgId);
        List<ServiceInfoGroup> serviceInfoGroups = serviceInfoGroupMapper.selectByExample(example);
        List<String> list = new ArrayList<>();
        if (serviceInfoGroups != null) {
            for (ServiceInfoGroup serviceInfoGroup : serviceInfoGroups) {
                ChannelInfo channelInfo = channelInfoMapper.selectByPrimaryKey(serviceInfoGroup.getSid());
                list.add(channelInfo.getChannelId());
            }
        }
        return list;
    }


}
