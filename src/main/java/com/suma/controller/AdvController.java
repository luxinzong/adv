package com.suma.controller;

import com.google.common.collect.Lists;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvLocationException;
import com.suma.exception.AdvRequestException;
import com.suma.exception.AdvTypeException;
import com.suma.exception.InfoVersionException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.StringUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@RestController
@Log4j
public class     AdvController {

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
    @Autowired
    private InfoVersionService infoVersionService;
    @Autowired
    private AdvInfoService advInfoService;

    @RequestMapping("getAdvShowP")
    public AdvResponseVO getAdvShow1(AdvRequestVO requestVO) {
        return getAdvShow(requestVO);
    }


    /**
     * 切台广告、弹出广告
     * @param requestVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("getAdvShow")
    public AdvResponseVO getAdvShow(@RequestBody AdvRequestVO requestVO) {

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
        //判断频道参数
        if (StringUtils.isNotBlank(requestVO.getNetworkID())
                || StringUtils.isNotBlank(requestVO.getTsId())
                || StringUtils.isNotBlank(requestVO.getServiceID())) {
            //根据networkId,tsId,serviceId查询频道信息
            ServiceInfoExample serviceInfoExample = new ServiceInfoExample();
            ServiceInfoExample.Criteria criteria = serviceInfoExample.createCriteria();

            if (requestVO.getFreq() != null) {
                criteria = criteria.andFreqEqualTo(requestVO.getFreq());
            }
            criteria = serviceInfoService
                    .queryServiceByThreeId(requestVO.getNetworkID(), requestVO.getTsId(), requestVO.getServiceID(), criteria);
            List<ServiceInfo> serviceInfos = new ArrayList<>();

            if (criteria != null)
                serviceInfos = serviceInfoService.selectByExample(serviceInfoExample);
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

        List<AdvInfo> advInfos = advServiceGroupService.findAdvByGroups(groups, advType, serviceParamType, requestVO.getRegionCode(),requestVO.getClientId());
        //处理播发逻辑
        //advInfos = advInfos.stream().filter(p -> p.getStatus().equals(AdvContants.STATUS_PUTTING)).collect(Collectors.toList());
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

            //如果没有带请求参数channelId，返回时要把广告关联的频道带上
            if (serviceParamType.equals(AdvContants.SERVICE_TYPE_ON_DEMAND_ALL)) {
                String channelIds = null;
                if (advInfo.getReservedInt() == 1) {
                    channelIds = channelService.findALLChannelIds();
                } else {
                    channelIds = advServiceGroupService.findChannelIdsByAdvId(advInfo.getId());
                }
                advItem.setChannelIds(channelIds);
            }

            //图片或视频
            if (materialType.equals(AdvContants.IMAGE_MATERIAL) || materialType.equals(AdvContants.VEDIO_MATERIAL)) {
                List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
                //infoMaterials.sort(Comparator.comparingInt(InfoMaterial::getSequence));
                //List<AdvMaterial> materials = infoMaterialService.findMaterialByAdv(advInfo.getId());
                for (InfoMaterial infoMaterial : infoMaterials) {
                    AdvMaterial material = advMaterialService.findByPK(infoMaterial.getMaterialId());

                    AdvItem perAdvItem = new AdvItem();
                    BeanUtils.copyProperties(advItem, perAdvItem);

                    perAdvItem.setDuration(Long.valueOf(infoMaterial.getDuration()));
                    perAdvItem.setHref(material.getHref());
                    perAdvItem.setAdvURL(material.getFileUrl());
                    perAdvItem.setMD5(material.getMd5());
                    advItems.add(perAdvItem);
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

        }
        return advItems;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("bootAdv")
    public AdvResponseVO getUpToDateBootAdv(@RequestBody AdvRequestVO advRequestVO) {
        System.out.println(advRequestVO);
        //判断参数
        if (StringUtils.isAnyEmpty(advRequestVO.getSessionId(), advRequestVO.getClientId())) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_MISSING_PARAMETERS,advRequestVO.getSessionId());
        }
        AdvResponseVO advResponseVO = new AdvResponseVO();
        advResponseVO.setSessionId(advRequestVO.getSessionId());
        //根据advType和advTypeSubType，判断是否是开机广告
        Long advTypeId = checkAdvType(advRequestVO);
        //获取区域信息
        Integer regionId = Integer.valueOf(advRequestVO.getRegionCode());
        //获取当前广告版本信息
        Integer version = advRequestVO.getVersion();
        //获取最新版本信息
        //Integer updateVersion = infoVersionService.getAdvByRegionAndAdvTypeIdAndStatus(regionId, advTypeId, AdvContants.STATUS_PUTTING);
        Integer upToDateVersion = infoVersionService.getUpToDateVersion(regionId, advTypeId);
        //当前版本是最新版本
        AdvItem advItem = null;
        List<AdvItem> list = Lists.newArrayList();
        if (version.equals(upToDateVersion)) {
            throw new InfoVersionException(ExceptionConstants.INFO_VERSION_NOT_EXIST_UPDATE);
        } else {
            //若不是最新版本信息，则将最新版本信息推送给终端
            AdvInfo advInfo = infoVersionService.getAdvInfoByRegionIdAndAdvTypeIdAndVersion(regionId, advTypeId, upToDateVersion);
            if (advInfo != null) {
                //设置返回终端的广告类型
                advItem = advTypeService.setAdvItem(advTypeId);
                //设置位置信息
                advInfoService.setAdvLocation(advInfo,advItem);
                //设置素材类型
                advItem.setAssetType((long) advInfo.getMaterialType());
                //设置素材
                List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
                infoMaterialService.setAdvItem(infoMaterials, advItem,list);
            }
        }
        advResponseVO.setVersion(upToDateVersion);
        advResponseVO.setAdvItem(list);
        advResponseVO.setResultCode("0");
        advResponseVO.setCheckInterval(null);
        advResponseVO.setResultCount((long)list.size());
        return advResponseVO;
    }

    /**
     * 开机广告、植入广告、菜单广告、节目列表、音量条广告请求接口
     * @param advRequestVO
     * @return
     */
    @RequestMapping("bootAdv1")
    public AdvResponseVO getAllBootAdv(@RequestBody AdvRequestVO advRequestVO) {
        System.out.println(advRequestVO);
        //判断参数
        if (StringUtils.isAnyEmpty(advRequestVO.getSessionId(), advRequestVO.getClientId())) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_MISSING_PARAMETERS,advRequestVO.getSessionId());
        }
        AdvResponseVO advResponseVO = new AdvResponseVO();
        advResponseVO.setSessionId(advRequestVO.getSessionId());
        //根据advType和advTypeSubType，判断广告类型
        Long advTypeId = checkAdvType(advRequestVO);
        //获取区域信息
        Integer regionId = Integer.valueOf(advRequestVO.getRegionCode());
        List<AdvItem> list = Lists.newArrayList();
        List<AdvInfo> advInfoList = infoVersionService.getAdvInfoByRegionIdAndAdvTypeId(regionId, advTypeId);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            System.out.println(advInfoList);
            advInfoService.getMaterials(advTypeId, list, advInfoList);
        }
        if (advTypeId.equals(AdvContants.START_MACHINE_ADV_SUBTYPE_ID)) {
            advResponseVO.setVersion(2);
        }
        advResponseVO.setAdvItem(list);
        advResponseVO.setResultCode("0");
        advResponseVO.setCheckInterval(null);
        advResponseVO.setResultCount((long) list.size());
        return advResponseVO;
    }


    /**
     * 判断广告类型
     * @param advRequestVO
     */
    public Long checkAdvType(AdvRequestVO advRequestVO) {
        String advType = advRequestVO.getAdvType();
        String advTypeSubType = advRequestVO.getAdvSubType();
        Long advTypeId = advTypeService.getAdvTypeIdByAdvTypeAndSubType(advType, advTypeSubType);
        return advTypeId;
    }
}
