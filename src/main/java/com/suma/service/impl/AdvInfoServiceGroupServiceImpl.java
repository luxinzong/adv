package com.suma.service.impl;

import com.google.common.collect.Lists;
import com.suma.constants.AdvContants;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.dao.BaseDAO;
import com.suma.pojo.AdvInfoServiceGroup;
import com.suma.pojo.AdvInfoServiceGroupExample;
import com.suma.pojo.ServiceGroup;
import com.suma.service.AdvInfoServiceGroupService;
import com.suma.service.ServiceGroupService;
import com.suma.vo.AdvInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    AdvInfoServiceGroupMapper advInfoServiceGroupMapper;

    @Override
    public void deleteAdvServiceByAdvInfoId(Long id) {
        AdvInfoServiceGroupExample example3 = new AdvInfoServiceGroupExample();
        example3.createCriteria().andAdvInfoIdEqualTo(id);
        advInfoServiceGroupMapper.deleteByExample(example3);
    }

    @Autowired
    ServiceGroupService serviceGroupService;

    @Override
    public void deleteAdvServicenByAdvInfoId(List<Long> list) {
        if (!CollectionUtils.isEmpty(list)) {
            AdvInfoServiceGroupExample example3 = new AdvInfoServiceGroupExample();
            example3.createCriteria().andAdvInfoIdIn(list);
            advInfoServiceGroupMapper.deleteByExample(example3);
        }
    }

    //保存频道信息
    @Override
    public void saveServiceInfomation(AdvInfoVO advInfoVO, Long advInfoId) {
        if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_ACTIVE)) {
            AdvInfoServiceGroup advInfoServiceGroup = new AdvInfoServiceGroup();
            advInfoServiceGroup.setAdvInfoId(advInfoId);
            if (!CollectionUtils.isEmpty(advInfoVO.getServiceGroupIds())) {
                advInfoVO.getServiceGroupIds().forEach(serviceGroupId -> {
                    advInfoServiceGroup.setType(advInfoVO.getType());
                    advInfoServiceGroup.setServiceGroupId(serviceGroupId);
                    advInfoServiceGroupMapper.insert(advInfoServiceGroup);
                });
            }
        } else if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_SLEEP)) {
            AdvInfoServiceGroup advInfoServiceGroup = new AdvInfoServiceGroup();
            advInfoServiceGroup.setAdvInfoId(advInfoId);
            advInfoServiceGroup.setType(advInfoVO.getType());
            advInfoServiceGroup.setServiceGroupId(0L);
            advInfoServiceGroupMapper.insert(advInfoServiceGroup);
        } else {
            //查询所有的频道分组 //TODO
            List<ServiceGroup> serviceGroups = serviceGroupService.findALL();
            serviceGroups.forEach(serviceGroup -> {
                AdvInfoServiceGroup advInfoServiceGroup = new AdvInfoServiceGroup();
                advInfoServiceGroup.setAdvInfoId(advInfoId);
                advInfoServiceGroup.setServiceGroupId(serviceGroup.getSgid());
                advInfoServiceGroup.setType(advInfoVO.getType());
                advInfoServiceGroupMapper.insert(advInfoServiceGroup);
            });
        }
    }

    @Override
    public void updateServiceGroup(AdvInfoVO advInfoVO) {
        if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_ACTIVE)) {
            if (!CollectionUtils.isEmpty(advInfoVO.getServiceGroupIds())) {
                advInfoVO.getServiceGroupIds().forEach(id ->{
                    AdvInfoServiceGroup advInfoServiceGroup = new AdvInfoServiceGroup();
                    advInfoServiceGroup.setAdvInfoId(advInfoVO.getId());
                    advInfoServiceGroup.setServiceGroupId(id);
                    advInfoServiceGroup.setType(advInfoVO.getType());
                    advInfoServiceGroupMapper.insert(advInfoServiceGroup);
                });
            }
        }
    }

    @Autowired
    private AdvInfoServiceGroupMapper infoServiceGroupMapper;

    @Override
    public List<ServiceGroup> findGroupNamesByAdvId(Long advInfoId) {
        AdvInfoServiceGroupExample infoServiceGroupExample = new AdvInfoServiceGroupExample();
        infoServiceGroupExample.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        List<AdvInfoServiceGroup> advInfoServiceGroups = infoServiceGroupMapper.selectByExample(infoServiceGroupExample);
        List<ServiceGroup> serviceGroups = new ArrayList<>();
        if(!CollectionUtils.isEmpty(advInfoServiceGroups)) {
            for (AdvInfoServiceGroup advInfoServiceGroup : advInfoServiceGroups) {
                ServiceGroup serviceGroup = serviceGroupService.findByPK(advInfoServiceGroup.getServiceGroupId());
                serviceGroups.add(serviceGroup);
            }
        }
        return serviceGroups;
    }

    @Override
    public void getServiceGroup(AdvInfoVO advInfoVO) {
        if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_ACTIVE)) {
            AdvInfoServiceGroupExample example2 = new AdvInfoServiceGroupExample();
            example2.createCriteria().andAdvInfoIdEqualTo(advInfoVO.getId());
            List<AdvInfoServiceGroup> list = advInfoServiceGroupMapper.selectByExample(example2);
            List<String> serviceGroupNames = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(advInfoServiceGroup -> {
                    serviceGroupNames.add(serviceGroupService.findByPK(advInfoServiceGroup.getServiceGroupId()).getGroupName());
                });
                advInfoVO.setServiceGroupNames(serviceGroupNames);
            }
        }
    }
}
