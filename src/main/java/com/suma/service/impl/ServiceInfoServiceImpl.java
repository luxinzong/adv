package com.suma.service.impl;

import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.dao.ServiceInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.*;
import com.suma.service.NetworkService;
import com.suma.service.ServiceInfoService;
import com.suma.service.TsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Autowired
    private TsService tsService;
    @Autowired
    private NetworkService networkService;

    @Transactional
    @Override
    public int deleteByPK(Long id) {

        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSidEqualTo(id);
        serviceInfoGroupMapper.deleteByExample(example);
        return super.deleteByPK(id);
    }

    @Override
    public void checkDuplicate(ServiceInfo serviceInfo) {
        ServiceInfoExample example = new ServiceInfoExample();

        if (serviceInfo.getId() != null) {
            ServiceInfo oldInfo = findByPK(serviceInfo.getId());
            if (serviceInfo.getServiceId().equals(oldInfo.getServiceId())
                    && serviceInfo.getServiceName().equals(oldInfo.getServiceName())) {
                return;
            } else if (serviceInfo.getServiceId().equals(oldInfo.getServiceId())) {
                example.createCriteria().andServiceNameEqualTo(serviceInfo.getServiceName());
                List<ServiceInfo> serviceInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(serviceInfos)) {
                    throw new BaseException("该ServiceName已存在");
                }
                return;
            } else if (serviceInfo.getServiceName().equals(oldInfo.getServiceName())) {
                example.createCriteria().andServiceIdEqualTo(serviceInfo.getServiceId());
                List<ServiceInfo> serviceInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(serviceInfos)) {
                    throw new BaseException("该ServiceId已存在");
                }
                return;
            }
        }

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
    }

    @Override
    public ServiceInfoExample.Criteria queryServiceByThreeId(String networkId, String tsId, String serviceId, ServiceInfoExample.Criteria criteria) {
        List<Long> tIds = new ArrayList<>();

        TsInfoExample tsExample = new TsInfoExample();
        TsInfoExample.Criteria tsCriteria = tsExample.createCriteria();
        if (StringUtils.isNotBlank(networkId)) {
            Long nId = networkService.findPKByNetworkId(networkId);
            if (nId != null)
                tsCriteria = tsCriteria.andNidEqualTo(nId);
            else
                return null;
        }
        if (StringUtils.isNotBlank(tsId)) {
            tsCriteria.andTsIdEqualTo(tsId);
        }

        List<TsInfo> tsInfos = tsService.selectByExample(tsExample);
        for (TsInfo tsInfo : tsInfos) {
            tIds.add(tsInfo.getId());
        }

        if (tIds.size() == 0 && (networkId != null || tsId != null)) {
            return null;
        }

        if (tIds.size() > 0) {
            criteria = criteria.andTidIn(tIds);
        }
        if (StringUtils.isNotBlank(serviceId)) {
            criteria.andServiceIdEqualTo(serviceId);
        }
        return criteria;
    }
}
