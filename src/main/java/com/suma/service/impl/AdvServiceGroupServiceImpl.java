package com.suma.service.impl;

import com.suma.constants.AdvContants;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvServiceGroupService;
import com.suma.service.ServiceInfoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/24
 * @description:
 */
@Service
public class AdvServiceGroupServiceImpl extends BaseServiceImpl<AdvInfoServiceGroup, AdvInfoServiceGroupExample, Long>
        implements AdvServiceGroupService {
    @Resource
    public void setBaseDao(AdvInfoServiceGroupMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private ServiceInfoGroupService serviceInfoGroupService;

    @Override
    public List<AdvInfo> findAdvByGroups(List<ServiceGroup> groups, AdvType advType) {
        List<AdvInfo> advInfos = new ArrayList<>();
        for (ServiceGroup group : groups) {
            AdvInfoServiceGroupExample example = new AdvInfoServiceGroupExample();
            example.createCriteria().andServiceGroupIdEqualTo(group.getSgid());
            List<AdvInfoServiceGroup> advInfoServiceGroups = selectByExample(example);
            if (!CollectionUtils.isEmpty(advInfoServiceGroups)) {
                for (AdvInfoServiceGroup advInfoServiceGroup : advInfoServiceGroups) {
                    AdvInfoExample advInfoExample = new AdvInfoExample();
                    advInfoExample.createCriteria().andAdvTypeIdEqualTo(advType.getId()).andIdEqualTo(advInfoServiceGroup.getAdvInfoId());
                    List<AdvInfo> list = advInfoService.selectByExample(advInfoExample);
                    if (!CollectionUtils.isEmpty(list)) {
                        AdvInfo advInfo = list.get(0);
                        advInfos.add(advInfo);
                    }
                }
            }
        }
        //处理未关联频道分组的广告
        AdvInfoExample advInfoExample = new AdvInfoExample();
        advInfoExample.createCriteria().andReservedIntEqualTo(AdvContants.ADV_SERVICE_NOT_ASSOCIATE);
        List<AdvInfo> infosNotAssociate = advInfoService.selectByExample(advInfoExample);
        advInfos.addAll(infosNotAssociate);

        return advInfos;
    }

    @Override
    public String findChannelIdsByAdvId(Long id) {

        AdvInfoServiceGroupExample advInfoServiceGroupExample = new AdvInfoServiceGroupExample();
        advInfoServiceGroupExample.createCriteria().andAdvInfoIdEqualTo(id).andTypeEqualTo(AdvContants.SERVICE_TYPE_ON_DEMAND);
        List<AdvInfoServiceGroup> advInfoServiceGroups = selectByExample(advInfoServiceGroupExample);

        StringBuilder channelIds = new StringBuilder();

        for (AdvInfoServiceGroup advInfoServiceGroup : advInfoServiceGroups) {
            channelIds.append(serviceInfoGroupService.findServicesByGroupId(advInfoServiceGroup.getServiceGroupId()).get(0)).append(",");
        }

        if (channelIds.length() > 0)
            channelIds.deleteCharAt(channelIds.length() - 1);
        return channelIds.toString();
    }
}
