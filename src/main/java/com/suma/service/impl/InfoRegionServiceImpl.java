package com.suma.service.impl;

import com.google.common.collect.Lists;
import com.suma.dao.*;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.InfoRegion;
import com.suma.pojo.InfoRegionExample;
import com.suma.service.InfoRegionService;
import com.suma.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/11/6 0006
 * @description
 */
@Service
public class InfoRegionServiceImpl extends BaseServiceImpl<InfoRegion, InfoRegionExample, Long> implements InfoRegionService {

    @Resource
    public void setBaseDao(InfoRegionMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private InfoRegionMapper infoRegionMapper;
    @Autowired
    private AdvRegionService advRegionService;

    @Autowired
    private AdvInfoServiceGroupMapper advInfoServiceGroupMapper;

    @Autowired
    private ServiceGroupService serviceGroupService;

    @Override
    public List<Long> selectAdvByRegion(Integer regionId) {
        InfoRegionExample example = new InfoRegionExample();
        example.createCriteria().andRegionIdEqualTo(regionId);
        List<InfoRegion> infoRegions =  infoRegionMapper.selectByExample(example);
        List<Long> list = Lists.newArrayList();
        infoRegions.forEach((infoRegion) ->{
            list.add(infoRegion.getAdvInfoId());
        });
        return list;
    }



    /**
     * 根据Id查出对应区域的名称
     * @param id
     * @return
     */
    @Override
    public List<String> getRegionNames(Long id) {
        InfoRegionExample example3 = new InfoRegionExample();
        example3.createCriteria().andAdvInfoIdEqualTo(id);
        List<InfoRegion> infoRegions = infoRegionMapper.selectByExample(example3);
        List<String> regionNames = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(infoRegions)) {
            infoRegions.forEach(infoRegion -> {
                regionNames.add(advRegionService.selectAdvRegionById(infoRegion.getRegionId()).getRegionName());
            });
        }
        return regionNames;
    }

    @Override
    public void  deleteByAdvInfoId(Long advInfoId){
        InfoRegionExample example = new InfoRegionExample();
        example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        infoRegionMapper.deleteByExample(example);
    }

    @Override
    public void saveInfoRegion(List<Integer> regionIds, Long advInfoId) {
        if (!CollectionUtils.isEmpty(regionIds)) {
            regionIds.forEach(regionId -> {
                InfoRegion infoRegion = new InfoRegion();
                infoRegion.setAdvInfoId(advInfoId);
                infoRegion.setRegionId(regionId);
                infoRegionMapper.insert(infoRegion);
            });
        } else {
            throw new AdvInfoException("未填写区域信息");
        }
    }

    @Override
    public void deleteByAdvInfoIds(List<Long> advInfoIds) {
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            InfoRegionExample example = new InfoRegionExample();
            example.createCriteria().andAdvInfoIdIn(advInfoIds);
            infoRegionMapper.deleteByExample(example);
        }
    }

    @Override
    public List<Integer> getRegionIds(Long advInfoId) {
        InfoRegionExample infoRegionExample = new InfoRegionExample();
        infoRegionExample.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        List<Integer> list = Lists.newArrayList();
        infoRegionMapper.selectByExample(infoRegionExample).stream().forEach((infoRegion -> {
            list.add(infoRegion.getRegionId());
        }));
        return list;
    }
}
