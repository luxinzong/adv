
package com.suma.service.impl;

import com.suma.dao.ChannelInfoMapper;
import com.suma.dao.ServiceGroupMapper;
import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.dao.ServiceInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.*;
import com.suma.service.ServiceGroupService;
import com.suma.service.ServiceInfoGroupService;
import com.suma.vo.ServiceGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@Service
public class ServiceGruopServiceImpl extends BaseServiceImpl<ServiceGroup, ServiceGroupExample, Long> implements ServiceGroupService {
    @Resource
    public void setBaseDao(ServiceGroupMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private ServiceGroupMapper serviceGroupMapper;
    @Autowired
    private ServiceInfoGroupService serviceInfoGroupService;
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;
    @Autowired
    private ChannelInfoMapper channelInfoMapper;

    @Override
    public List<ServiceGroup> findByName(String groupName) {
        ServiceGroupExample example = new ServiceGroupExample();
        example.createCriteria().andGroupNameEqualTo(groupName);
        return serviceGroupMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(ServiceGroup serviceGroup, List<String> serviceNames) {
        try {
            serviceGroupMapper.insert(serviceGroup);
            addInfoGroup(serviceGroup, serviceNames);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("增加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ServiceGroup serviceGroup, List<String> serviceNames) {
        serviceInfoGroupService.deleteByGroupId(serviceGroup.getSgid());
        try {
            serviceGroupMapper.updateByPrimaryKey(serviceGroup);
            addInfoGroup(serviceGroup, serviceNames);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("修改失败");
        }
    }

    private void addInfoGroup(ServiceGroup serviceGroup, List<String> serviceNames) {
        if (serviceGroup.getType() == 0) {
            for (String serviceName : serviceNames) {
                ServiceInfoGroup serviceInfoGroup = new ServiceInfoGroup();
                serviceInfoGroup.setSgid(serviceGroup.getSgid());
                ServiceInfoExample example = new ServiceInfoExample();
                example.createCriteria().andServiceNameEqualTo(serviceName);
                List<ServiceInfo> serviceInfos = serviceInfoMapper.selectByExample(example);
                if (serviceInfos != null && serviceInfos.size() > 0) {
                    serviceInfoGroup.setSid(serviceInfos.get(0).getId());
                    serviceInfoGroupService.save(serviceInfoGroup);
                }
            }
        } else {
            for (String serviceName : serviceNames) {
                ServiceInfoGroup serviceInfoGroup = new ServiceInfoGroup();
                serviceInfoGroup.setSgid(serviceGroup.getSgid());
                ChannelInfoExample example = new ChannelInfoExample();
                example.createCriteria().andChannelNameEqualTo(serviceName);
                List<ChannelInfo> channelInfos = channelInfoMapper.selectByExample(example);
                if (channelInfos != null && channelInfos.size() > 0) {
                    serviceInfoGroup.setSid(channelInfos.get(0).getId());
                    serviceInfoGroupService.save(serviceInfoGroup);
                }
            }
        }
    }
}
