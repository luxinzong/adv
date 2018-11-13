package com.suma.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvInfoMapper;
import com.suma.dao.AdvMaterialMapper;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvLocationService;
import com.suma.service.InfoMaterialService;
import com.suma.service.InfoRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            advInfoMapper.deleteByExample(example);
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
    public List<AdvInfo> getAdvInfoByRegionIdAndAdvTypeId(List<Long> advInfoIds, Long advTypeId) {
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
     * @param pageNum
     * @param pageSize
     * @param advTypeId
     * @return
     */
    @Override
    public List<AdvInfo> selectAdvInfoByNameAndStatusAndOthor(
            Integer status, String name, String startDate, String endDate,
            Integer pageNum, Integer pageSize, String advTypeId) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("status", status);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("advTypeId",advTypeId);
        //根据map查询出广告信息
        List<AdvInfo> advInfoList = selectAdvInfo(map);
        return advInfoList;
    }

    @Override
    public int deleteAdvRelationInfo(List<Long> advInfoIds) {
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(advInfoId -> {
                //删除广告信息
                deleteByPK(advInfoId);
                //删除广告位
                advLocationService.deleteByPK(findByPK(advInfoId).getAdvLocationId());
                //删除有效区域
                infoRegionService.deleteByAdvInfoId(advInfoId);
                //删除对应关系
                infoMaterialService.deleteByAdvInfoId(advInfoId);
            });
        }
        return 0;
    }
}
