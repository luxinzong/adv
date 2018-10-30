package com.suma.controller;

import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvInfoServiceGroupMapper;
import com.suma.exception.AdvRequestException;
import com.suma.pojo.*;
import com.suma.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@RestController
public class AdvController {

    @Autowired
    private AdvTypeService advTypeService;
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private ServiceInfoGroupService serviceInfoGroupService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private AdvServiceGroupService advServiceGroupService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private AdvFlywordService advFlywordService;
    @Autowired
    private InfoFlywordService infoFlywordService;
    @Autowired
    private InfoMaterialService infoMaterialService;
    @Autowired
    private AdvMaterialService advMaterialService;
    @Autowired
    private ServiceGroupService serviceGroupService;

    @RequestMapping("getAdvShow")
    public AdvResponseVO getAdvShow(AdvRequestVO requestVO) {

        //判断参数
        if (StringUtils.isAnyBlank(requestVO.getSessionId(), requestVO.getClientId())) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_MISSING_PARAMETERS, requestVO.getSessionId());
        }

        AdvResponseVO responseVO = new AdvResponseVO();
        responseVO.setSessionId(requestVO.getSessionId());

        //判断广告类型
        AdvTypeExample example = new AdvTypeExample();
        example.createCriteria().andAdvtypeEqualTo(requestVO.getAdvType()).andAdvsubtypeEqualTo(requestVO.getAdvSubType());
        List<AdvType> advTypes = advTypeService.selectByExample(example);
        if (CollectionUtils.isEmpty(advTypes)) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_TYPE_NOT_EXIST, requestVO.getSessionId());
        }
        AdvType advType = advTypes.get(0);
        List<ServiceGroup> groups = new ArrayList<>();

        Integer serviceParamType = null;

        if (StringUtils.isNotBlank(requestVO.getNetworkID())
                || StringUtils.isNotBlank(requestVO.getTsId())
                || StringUtils.isNotBlank(requestVO.getServiceID())) {
            //根据networkId,tsId,serviceId查询service
            ServiceInfoExample serviceInfoExample = new ServiceInfoExample();
            ServiceInfoExample.Criteria criteria = serviceInfoService
                    .queryServiceByThreeId(requestVO.getNetworkID(), requestVO.getTsId(), requestVO.getServiceID(), serviceInfoExample);
            if (requestVO.getFreq() != null)
                criteria.andFreqEqualTo(requestVO.getFreq());
            List<ServiceInfo> serviceInfos = serviceInfoService.selectByExample(serviceInfoExample);
            if (CollectionUtils.isEmpty(serviceInfos)) {
                throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_SERVICE_NOT_EXIST, requestVO.getSessionId());
            }
            ServiceInfo serviceInfo = serviceInfos.get(0);
            groups = serviceInfoGroupService.findGroupBySId(serviceInfo.getId(), AdvContants.SERVICE_TYPE_LIVE);
            serviceParamType = AdvContants.SERVICE_TYPE_LIVE;
        } else if (StringUtils.isNotBlank(requestVO.getChannelID())) {
            ChannelInfoExample channelInfoExample = new ChannelInfoExample();
            channelInfoExample.createCriteria().andChannelIdEqualTo(requestVO.getChannelID());
            List<ChannelInfo> channelInfos = channelService.selectByExample(channelInfoExample);
            if (CollectionUtils.isEmpty(channelInfos)) {
                throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_CHANNEL_NOT_EXIST, requestVO.getSessionId());
            }
            ChannelInfo channelInfo = channelInfos.get(0);
            groups = serviceInfoGroupService.findGroupBySId(channelInfo.getId(), AdvContants.SERVICE_TYPE_ON_DEMAND);
            serviceParamType = AdvContants.SERVICE_TYPE_ON_DEMAND;
        } else {
            ServiceGroupExample serviceGroupExample = new ServiceGroupExample();
            serviceGroupExample.createCriteria().andTypeEqualTo(AdvContants.SERVICE_TYPE_ON_DEMAND);
            groups = serviceGroupService.selectByExample(serviceGroupExample);
            serviceParamType = AdvContants.SERVICE_TYPE_ON_DEMAND_ALL;
        }
        List<AdvInfo> advInfos = advServiceGroupService.findAdvByGroups(groups, advType);
        List<AdvItem> advItems = transform(advInfos, serviceParamType);

        responseVO.setResultCount((long) advItems.size());
        responseVO.setResultCode("0");
        responseVO.setAdvItem(advItems);
        responseVO.setCheckInterval(null);
        return responseVO;
    }

    private List<AdvItem> transform(List<AdvInfo> advInfos, Integer serviceParamType) {
        List<AdvItem> advItems = new ArrayList<>();
        for (AdvInfo advInfo : advInfos) {
            AdvItem advItem = new AdvItem();
            //填入广告类型
            AdvType advType = advTypeService.findByPK(advInfo.getAdvTypeId());
            advItem.setAdvType(advType.getAdvtype());
            advItem.setAdvSubType(advType.getAdvsubtype());

            //填入位置信息
            AdvLocation location = advLocationService.findByPK(advInfo.getAdvLocationId());
            BeanUtils.copyProperties(location, advItem);

            //素材类型
            Integer materialType = advInfo.getMaterialType();
            advItem.setAssetType(Long.valueOf(materialType));

            //图片或视频
            if (materialType.equals(AdvContants.IMAGE_MATERIAL) || materialType.equals(AdvContants.VEDIO_MATERIAL)) {
                List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
                List<AdvMaterial> materials = infoMaterialService.findMaterialByAdv(advInfo.getId());

                for (InfoMaterial infoMaterial : infoMaterials) {
                    AdvMaterial material = advMaterialService.findByPK(infoMaterial.getMaterialId());
                    advItem.setDuration(Long.valueOf(infoMaterial.getDuration()));
                    advItem.setHref(material.getHref());
                    advItem.setAdvURL(material.getFileUrl());
                    advItem.setMD5(material.getMd5());
                    advItems.add(advItem);
                }

            }
            //文字
            if (materialType.equals(AdvContants.TEXT_MATERIAL)) {
                List<Long> flywordIds = infoFlywordService.selectFlywordIds(advInfo.getId());
                AdvFlyWord flyWord = advFlywordService.findByPK(flywordIds.get(0));
                advItem.setContext(flyWord.getContent());
                advItem.setBackgroudColor(flyWord.getBackgroundColour());
                advItem.setSpeed(flyWord.getSpeed());
                advItem.setDirect(flyWord.getDirect());
                advItem.setDisplayTimes(flyWord.getDisplayTimes());
                advItem.setFontSize(flyWord.getFontSize());
                advItem.setFontColor(flyWord.getFontColour());
                advItem.setInterval(flyWord.getIntervalTime());
                advItems.add(advItem);
            }


            //如果没有带请求参数channelId，返回时要把广告关联的频道带上
            if (serviceParamType.equals(AdvContants.SERVICE_TYPE_ON_DEMAND_ALL)) {
                String channelIds = advServiceGroupService.findChannelIdsByAdvId(advInfo.getId());
                advItem.setChannelIds(channelIds);
            }


        }

        return advItems;
    }

}
