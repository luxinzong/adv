package com.suma.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.suma.constants.AdvContants;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.dao.BaseDAO;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvInfoServiceGroupService;
import com.suma.service.ServiceGroupService;
import com.suma.vo.AdvInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    AdvInfoService advInfoService;

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
                if (CollectionUtils.isEmpty(advInfoVO.getServiceGroupIds())) {
                    throw new AdvInfoException("未填写频道信息");
                }
                advInfoVO.getServiceGroupIds().forEach(serviceGroupId -> {
                    advInfoServiceGroup.setType(advInfoVO.getType());
                    advInfoServiceGroup.setServiceGroupId(serviceGroupId);
                    advInfoServiceGroupMapper.insert(advInfoServiceGroup);
                });
            }else{
                throw new AdvInfoException(AdvContants.SERVICE_GROUP_IS_NULL);
            }
        }
    }

    @Override
    public List<AdvInfo> getAdvInfo(List<Long> ids) {
        Set<Long> set = Sets.newConcurrentHashSet();
        AdvInfoServiceGroupExample example = new AdvInfoServiceGroupExample();
        example.createCriteria().andServiceGroupIdIn(ids);
        List<AdvInfoServiceGroup> list = selectByExample(example);
        List<Long> s = list.stream().map(AdvInfoServiceGroup::getAdvInfoId).collect(Collectors.toList());
        set.addAll(s);
        AdvInfoExample example1 = new AdvInfoExample();
        example1.createCriteria().andIdIn(Lists.newArrayList(set));
        List<AdvInfo> advInfoList = advInfoService.selectByExample(example1);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            return advInfoList;
        }
        return null;
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
            List<AdvInfoServiceGroup> list = selectByExample(example2);
            List<String> serviceGroupNames = Lists.newArrayList();
            List<Long> ServiceGroupIds = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(advInfoServiceGroup -> {
                    ServiceGroupIds.add(advInfoServiceGroup.getServiceGroupId());
                    //serviceGroupNames.add(serviceGroupService.findByPK(advInfoServiceGroup.getServiceGroupId()).getGroupName());
                });
                //advInfoVO.setServiceGroupNames(serviceGroupNames);
                advInfoVO.setServiceGroupIds(ServiceGroupIds);
                System.out.println(list.get(0).getType()+"我们都是");
                advInfoVO.setType(list.get(0).getType());
            }
        }
    }
}
