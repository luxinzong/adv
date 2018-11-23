package com.suma.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvInfoMapper;
import com.suma.dao.AdvMaterialMapper;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvLocationException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.StringUtil;
import com.suma.vo.AdvPutVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class AdvInfoServiceImpl extends BaseServiceImpl<AdvInfo, AdvInfoExample, Long> implements AdvInfoService {

    @Autowired
    AdvInfoMapper advInfoMapper;
    @Autowired
    AdvMaterialMapper advMaterialMapper;
    @Autowired
    InfoRegionService infoRegionService;
    @Autowired
    AdvLocationService advLocationService;
    @Autowired
    InfoMaterialService infoMaterialService;
    @Autowired
    InfoVersionService infoVersionService;
    @Autowired
    AdvInfoServiceGroupService advInfoServiceGroupService;
    @Autowired
    ServiceInfoGroupService serviceInfoGroupService;
    @Autowired
    AdvTypeService advTypeService;
    @Autowired
    InfoFlywordService infoFlywordService;

    @Resource
    public void setBaseDao(AdvInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public List<AdvInfo> selectAdvInfo(Map<String,Object> map) {
        return advInfoMapper.selectAdvInfo(map);
    }

    @Override
    public AdvInfo findById(Long id) {
        return advInfoMapper.findById(id);
    }


    @Override
    public List<AdvInfo> getAdvByIds(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            AdvInfoExample example = new AdvInfoExample();
            example.createCriteria().andIdIn(ids);
            return selectByExample(example);
        }
        return null;
    }

    @Override
    public List<AdvInfo> getPuttingAdv(AdvPutVO advPutVO) {
        AdvInfoExample example = new AdvInfoExample();
        AdvInfoExample.Criteria criteria = example.createCriteria();
        //弹出广告 播发
        criteria.andAdvTypeIdEqualTo(advPutVO.getAdvTypeId());
        if (advPutVO.getStatus() != null) {
            criteria.andStatusEqualTo(advPutVO.getStatus());
        } else {
            criteria.andStatusIn(Lists.newArrayList(AdvContants.STATUS_STOP, AdvContants.STATUS_PUTTING));
        }
        List<AdvInfo> list = selectByExample(example);
        List<Long> longList = list.stream().map(AdvInfo::getId).collect(Collectors.toList());
        if (advPutVO.getAdvTypeId().equals(AdvContants.START_MACHINE_ADV_SUBTYPE_ID)) {
            //获取某个版本的播发广告
            if (advPutVO.getVersion() != null) {
                longList = infoVersionService.getAdvPuttingByVersion(longList, advPutVO.getVersion());
            }
        }
        //获取某个区域的播发状态的广告
        if (advPutVO.getRegionId() != null) {
            longList =  infoRegionService.getAdvPuttingByRegion(longList, advPutVO.getRegionId());
        }
        //ca卡号
        longList = getAdvInfoIdsByCa(advPutVO, longList);
        //频道和播放类型
        getAdvInfoIdsByServiceAndType(advPutVO, longList);
        //起始时间
        getAdvInfoIdsByDate(advPutVO, longList);
        AdvInfoExample example1 = new AdvInfoExample();
        example1.createCriteria().andIdIn(longList);
        List<AdvInfo> advInfoList = selectByExample(example1);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            return advInfoList;
        }
        return null;
    }

    private void getAdvInfoIdsByDate(AdvPutVO advPutVO, List<Long> longList) {
        Map<String, Object> map = Maps.newConcurrentMap();
        map.put("startDate", advPutVO.getStartDate());
        map.put("endDate", advPutVO.getEndDate());
        List<AdvInfo> advInfoList1 = selectAdvInfoByDate(map);
        List<Long> list1 = null;
        if (!CollectionUtils.isEmpty(advInfoList1)) {
            list1 = advInfoList1.stream().map(AdvInfo::getId).collect(Collectors.toList());
        }
        if (list1 != null) {
            longList.retainAll(list1);
        }
    }

    private void getAdvInfoIdsByServiceAndType(AdvPutVO advPutVO, List<Long> longList) {
        if (advPutVO.getServiceId() != null) {
            List<ServiceGroup> serviceGroups = serviceInfoGroupService.findGroupBySId(advPutVO.getServiceId(), advPutVO.getType());
            if (!CollectionUtils.isEmpty(serviceGroups)) {
                List<Long> serviceGroupIds = serviceGroups.stream().map(ServiceGroup::getSgid).collect(Collectors.toList());
                List<AdvInfo> infoList = advInfoServiceGroupService.getAdvInfo(serviceGroupIds);
                List<Long> ids = infoList.stream().map(AdvInfo::getId).collect(Collectors.toList());
                longList.retainAll(ids);
            }
        }
    }

    private List<Long> getAdvInfoIdsByCa(AdvPutVO advPutVO, List<Long> longList) {
        AdvInfoExample example1 = new AdvInfoExample();
        example1.createCriteria().andReservedStringEqualTo(advPutVO.getCard()).andIdIn(longList);
        List<AdvInfo> advInfoList = selectByExample(example1);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            longList = advInfoList.stream().map(AdvInfo::getId).collect(Collectors.toList());
        }
        return longList;
    }

    @Override
    public List<AdvInfo> selectAdvInfoByDate(Map<String, Object> map) {
        return null;
    }

    @Override
    public void setBootLocation(AdvInfo advInfo) {
        AdvLocation advLocation = new AdvLocation();
        advLocation.setmHeight(720L);
        advLocation.setmWidth(1280L);
        advLocation.setyPosition(0L);
        advLocation.setxPosition(0L);
        advLocationService.save(advLocation);
        advInfo.setAdvLocationId(advLocation.getId());
    }


    @Override
    public Integer getAdvInfoVersionByAdvTypeId(Long advTypeId) {
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andAdvTypeIdEqualTo(advTypeId);
        List<AdvInfo> advInfoList = selectByExample(example);
        List<InfoVersion> infoVersions = infoVersionService.getInfoVersions(advInfoList);
        infoVersions.sort(Comparator.comparingInt(InfoVersion::getVersion));
        Collections.reverse(infoVersions);
        return infoVersions.get(0).getVersion();
    }

    /**
     * 更具广告ID删除广告位
     * @param advInfoIds
     * @return
     */
    @Override
    public int deleteAdvLocationByAdvInfoIds(List<Long> advInfoIds) {
        List<Long> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(id ->{
                if (findById(id) != null) {
                    list.add(findById(id).getAdvLocationId());
                }
            });
        }
        if (!CollectionUtils.isEmpty(list)) {
            AdvLocationExample example = new AdvLocationExample();
            example.createCriteria().andIdIn(list);
            advLocationService.deleteByExample(example);
        }
        return 0;
    }

    /**
     * 获取视频源
     * @return
     */
    @Override
    public List<AdvMaterial> getVedioMaterial(){
        AdvMaterialExample example = new AdvMaterialExample();
        example.createCriteria().andMaterialTypeEqualTo(3);
        return advMaterialMapper.selectByExample(example);
    }

    /**
     * 删除广告信息
     * @param list
     */
    @Override
    public void deleteByAdvInfoIds(List<Long> list) {
        if (!CollectionUtils.isEmpty(list)) {
            AdvInfoExample example = new AdvInfoExample();
            example.createCriteria().andIdIn(list);
            //判断广告是否处于播发状态
            /*List<AdvInfo> advInfoList = selectByExample(example);
            advInfoList.forEach(p->{
                if (p.getStatus().equals(AdvContants.STATUS_PUTTING)) {
                    throw new AdvInfoException(p.getName()+"广告处于播发状态，请停播后载进行删除");
                }
            });*/
            advInfoMapper.deleteByExample(example);
        }
    }

    /**
     * 获取资源
     * @param advTypeId
     * @param list
     * @param advInfoList
     */
    @Override
    public void getMaterials(Long advTypeId, List<AdvItem> list, List<AdvInfo> advInfoList) {
        advInfoList.forEach(advInfo -> {
            setAdvListByOne(advTypeId, list, advInfo);
        });
    }

    /**
     * 设置单个广告的信息
     * @param advTypeId
     * @param list
     * @param advInfo
     */
    private void setAdvListByOne(Long advTypeId, List<AdvItem> list, AdvInfo advInfo) {
        if (advInfo != null) {
            AdvItem advItem = null;
            //设置返回终端的广告类型
            if (advTypeId != null) {
                advItem = advTypeService.setAdvItem(advTypeId);
            }
            //设置广告位
            setAdvLocation(advInfo, advItem);
            //设置素材类型
            advItem.setAssetType((long) advInfo.getMaterialType());
            //设置素材
            List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
            infoMaterialService.setAdvItem(infoMaterials, advItem,list);
        }
    }

    /**
     * 广告 导入器功能实现
     * @param map
     * @param advInfo
     */
    @Override
    public void setAdvMapByOne(Map<AdvItem, MultipartFile> map, AdvInfo advInfo) {
        AdvItem advItem = new AdvItem();
        if (advInfo != null) {
            setAdvLocation(advInfo, advItem);
            advItem.setAssetType((long) advInfo.getMaterialType());
            if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL) | advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
                List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
                infoMaterialService.setAdvItemByOne(infoMaterials, advItem, map);
            } else {
                List<AdvFlyWord> advFlyWords = infoFlywordService.getAdvFlyWords(advInfo.getId());
                advFlyWords.forEach(advFlyWord -> {
                    BeanUtils.copyProperties(advFlyWord, advItem);
                    map.put(advItem, null);
                });
            }
        }
    }


    /**
     * 设置广告位
     * @param advInfo
     * @param advItem
     */
    @Override
    public void setAdvLocation(AdvInfo advInfo, AdvItem advItem) {
        //设置位置信息
        Boolean flag = (advInfo.getAdvTypeId().equals(AdvContants.BEFORE_MOVIE_ADV_SUBTYPE) && advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)
                | advInfo.getAdvTypeId().equals(AdvContants.START_MACHINE_ADV_SUBTYPE_ID));
        if (!flag) {
            AdvLocation advLocation = advLocationService.findByPK(advInfo.getAdvLocationId());
            if (advLocation == null) {
                throw new AdvLocationException(ExceptionConstants.ADV_LOCATION_IS_NOT_EXIST);
            }
            BeanUtils.copyProperties(advLocation, advItem);
        }
    }

    /**
     * 判断广告是否存在
     * @param advInfo
     */
    @Override
    public void judgeAdvInfo(AdvInfo advInfo) {
        String name = advInfo.getName();
        //检查该名称广告是否存在，如果存在提示用户该广告名称已经存在
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<AdvInfo> advInfoList = advInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_NAME_IS_EXIT);
        }
    }

    /**
     * 根据广告ID 和广告类型 把广告信息查询出来
     * @param advInfoIds
     * @param advTypeId
     * @return
     */
    @Override
    public List<AdvInfo> getAdvInfoByAdvInfoIdIdAndAdvTypeId(List<Long> advInfoIds, Long advTypeId) {
        AdvInfoExample example = new AdvInfoExample();
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            example.createCriteria().andIdIn(advInfoIds).andAdvTypeIdEqualTo(advTypeId);
        }
        return selectByExample(example);
    }

    /**
     *  按条件查询所有符合条件的广告
     * @param status
     * @param name
     * @param startDate
     * @param endDate
     * @param advTypeId
     * @return
     */
    @Override
    public List<AdvInfo> selectAdvInfoByNameAndStatusAndOthor(
            Integer status, String name, String startDate, String endDate, String advTypeId) {
        Map<String, Object> map = Maps.newConcurrentMap();
        if (StringUtils.isNotBlank(name)) {
            map.put("name", name);
        }
        if (status != null) {
            map.put("status", status);
        }
        if (StringUtils.isNotBlank(startDate)) {
            map.put("startDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            map.put("endDate", endDate);
        }
        if (StringUtils.isNotBlank(advTypeId)) {
            map.put("advTypeId",advTypeId);
        }
        //根据map查询出广告信息
        List<AdvInfo> advInfoList = selectAdvInfo(map);
        return advInfoList;
    }

    @Override
    public int deleteAdvRelationInfo(List<Long> advInfoIds) {
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(advInfoId -> {
                AdvInfo advInfo = findByPK(advInfoId);
                if (advInfo == null) {
                    throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
                }
                //删除广告位
                AdvLocation advLocation  = advLocationService.findByPK(advInfo.getAdvLocationId());
                if (advLocation != null) {
                    advLocationService.deleteByPK(advInfo.getAdvLocationId());
                }
                //删除有效区域
                List<Integer> list = infoRegionService.getRegionIds(advInfoId);
                if (!CollectionUtils.isEmpty(list)) {
                    infoRegionService.deleteByAdvInfoId(advInfoId);
                }
                //删除素材对应关系
                List<InfoMaterial> infoMaterialList = infoMaterialService.findByAdv(advInfoId);
                if (!CollectionUtils.isEmpty(infoMaterialList)) {
                    infoMaterialService.deleteByAdvInfoId(advInfoId);
                }
                //删除广告信息
                deleteByPK(advInfoId);
            });
        }
        return 0;
    }
}
