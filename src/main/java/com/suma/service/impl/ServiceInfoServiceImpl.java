package com.suma.service.impl;

import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.dao.ServiceInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.ServiceInfo;
import com.suma.pojo.ServiceInfoExample;
import com.suma.pojo.ServiceInfoGroup;
import com.suma.pojo.ServiceInfoGroupExample;
import com.suma.service.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Autowired
    private ServiceInfoGroupMapper serviceInfoGroupMapper;

    @Transactional
    @Override
    public int deleteByPK(Long id) {

        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSidEqualTo(id);
        serviceInfoGroupMapper.deleteByExample(example);
        return super.deleteByPK(id);
    }

    @Override
    public int save(ServiceInfo serviceInfo) {
        ServiceInfoExample example = new ServiceInfoExample();

        example.or().andServiceIdEqualTo(serviceInfo.getServiceId());
        List<ServiceInfo> serviceInfos = selectByExample(example);
        if (serviceInfos != null && serviceInfos.size() > 0) {
            throw new BaseException("该ServiceId已存在");
        }

        example.clear();

        example.or().andServiceNameEqualTo(serviceInfo.getServiceName());
        List<ServiceInfo> serviceInfos1 = selectByExample(example);
        if (serviceInfos1 != null && serviceInfos1.size() > 0) {
            throw new BaseException("该ServiceName已存在");
        }

        return super.save(serviceInfo);
    }
}
