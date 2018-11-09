package com.suma.service.impl;

import com.suma.constants.AdvContants;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvServiceGroupService;
import com.suma.service.InfoRegionService;
import com.suma.service.ServiceInfoGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

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
    @Autowired
    private InfoRegionService infoRegionService;

    @Override
    public List<AdvInfo> findAdvByGroups(List<ServiceGroup> groups, AdvType advType, Integer serviceParamType, String regionCode, String clientId) {
        List<AdvInfo> advInfos = new ArrayList<>();
        for (ServiceGroup group : groups) {
            AdvInfoServiceGroupExample example = new AdvInfoServiceGroupExample();
            example.createCriteria().andServiceGroupIdEqualTo(group.getSgid());
            List<AdvInfoServiceGroup> advInfoServiceGroups = selectByExample(example);
            if (!CollectionUtils.isEmpty(advInfoServiceGroups)) {
                for (AdvInfoServiceGroup advInfoServiceGroup : advInfoServiceGroups) {
                    AdvInfoExample advInfoExample = new AdvInfoExample();
                    AdvInfoExample.Criteria criteria = advInfoExample.createCriteria().andAdvTypeIdEqualTo(advType.getId()).andIdEqualTo(advInfoServiceGroup.getAdvInfoId());
                    List<AdvInfo> list = advInfoService.selectByExample(advInfoExample);

                    //处理区域
                    if (regionCode != null)
                        list = handleRegion(list, regionCode);
                    if (!CollectionUtils.isEmpty(list)) {
                        AdvInfo advInfo = list.get(0);
                        advInfos.add(advInfo);
                    }
                }
            }
        }
        //处理未关联频道分组的广告
        AdvInfoExample advInfoExample = new AdvInfoExample();
        AdvInfoExample.Criteria criteria = advInfoExample.createCriteria();
        //直播
        if (serviceParamType.equals(AdvContants.SERVICE_TYPE_LIVE)) {
            criteria = criteria.andReservedIntIn(Arrays.asList(AdvContants.ADV_SERVICE_NOT_ASSOCIATE, AdvContants.ADV_NOT_ASSOCIATE)).andAdvTypeIdEqualTo(advType.getId());
        } else {//点播
            criteria = criteria.andReservedIntIn(Arrays.asList(AdvContants.ADV_CHANNEL_NOT_ASSOCIATE, AdvContants.ADV_NOT_ASSOCIATE)).andAdvTypeIdEqualTo(advType.getId());
        }
        List<AdvInfo> infosNotAssociate = advInfoService.selectByExample(advInfoExample);

        //处理区域
        if (regionCode != null) {
            infosNotAssociate = handleRegion(infosNotAssociate, regionCode);
        }

        advInfos.addAll(infosNotAssociate);
        return handleCAOrSerial(advInfos, clientId);
    }

    @Override
    public String findChannelIdsByAdvId(Long id) {

        AdvInfoServiceGroupExample advInfoServiceGroupExample = new AdvInfoServiceGroupExample();
        advInfoServiceGroupExample.createCriteria().andAdvInfoIdEqualTo(id).andTypeEqualTo(AdvContants.SERVICE_TYPE_ON_DEMAND);
        List<AdvInfoServiceGroup> advInfoServiceGroups = selectByExample(advInfoServiceGroupExample);

        Set<String> channelIdList = new HashSet<>();

        for (AdvInfoServiceGroup advInfoServiceGroup : advInfoServiceGroups) {
            channelIdList.addAll(serviceInfoGroupService.findChannelIdsByGroupId(advInfoServiceGroup.getServiceGroupId()));
        }

        return StringUtils.join(channelIdList, ",");
    }

    private List<AdvInfo> handleRegion(List<AdvInfo> list, String regionCode) {
        List<AdvInfo> result = new ArrayList<>();

        List<Long> advIds = infoRegionService.selectAdvByRegion(Integer.valueOf(regionCode));
        if (list != null)
            if (!CollectionUtils.isEmpty(advIds))
                for (AdvInfo advInfo : list) {
                    if (advIds.contains(advInfo.getId())) {
                        result.add(advInfo);
                    }
                }
        return result;
    }

    private List<AdvInfo> handleCAOrSerial(List<AdvInfo> allAdvInfo, String clientId) {
        List<AdvInfo> result = new ArrayList<>();
        for (AdvInfo advInfo : allAdvInfo) {
            String caOrigin = advInfo.getReservedString();
            String[] caOuter = caOrigin.split(",");

            for (String caInner : caOuter) {
                String[] cas = caInner.split("-");
                if (cas.length > 1) {
                    if (Long.valueOf(clientId) >= Long.valueOf(cas[0]) && Long.valueOf(clientId) <= Long.valueOf(cas[1])) {
                        result.add(advInfo);
                    }
                } else {
                    if (clientId.equals(caInner)) {
                        result.add(advInfo);
                    }
                }
            }
        }
        return result;
    }
}
