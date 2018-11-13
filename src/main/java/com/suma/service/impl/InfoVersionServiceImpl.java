package com.suma.service.impl;

import com.google.common.collect.Lists;
import com.suma.dao.BaseDAO;
import com.suma.dao.InfoVersionMapper;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.InfoVersion;
import com.suma.pojo.InfoVersionExample;
import com.suma.service.AdvInfoService;
import com.suma.service.InfoRegionService;
import com.suma.service.InfoVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: luxinzong
 * @date: 2018/11/12 0012
 * @description
 */
@Service
public class InfoVersionServiceImpl extends BaseServiceImpl<InfoVersion, InfoVersionExample, Long>
        implements InfoVersionService {
    @Resource
    public void setBaseDao(InfoVersionMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    AdvInfoService advInfoService;
    @Autowired
    InfoRegionService infoRegionService;

    /**
     * 根据广告ID获取广告版本信息
     * @param advInfoId
     * @return
     */
    @Override
    public InfoVersion getInfoVersionByAdvInfoId(Long advInfoId) {
        if (advInfoId != null) {
            System.out.println(advInfoId);
            InfoVersionExample example = new InfoVersionExample();
            example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
            List<InfoVersion> list = selectByExample(example);
            if (!CollectionUtils.isEmpty(list)) {
                return  list.get(0);
            }
        }
        return null;
    }

    /**
     * 查询所有的开机广告
     * @param regionId
     * @param advTypeId
     * @return
     */
    @Override
    public List<AdvInfo> getBootAdv(Integer regionId, Long advTypeId) {
        //查出对应区域的所有广告ID
        List<Long> list = infoRegionService.selectAdvByRegion(regionId);
        System.out.println(list);
        //查出所有开机广告
        List<AdvInfo> advInfoList = advInfoService.getAdvInfoByRegionIdAndAdvTypeId(list, advTypeId);
        System.out.println(advInfoList);
        return advInfoList;
    }

    /**
     * 获取某个区域内，开机广告的最新版本号
     * @param regionId
     * @param advTypeId
     * @return
     */
    @Override
    public Integer getUpToDateVersion(Integer regionId, Long advTypeId) {
        List<InfoVersion> infoVersions = getInfoVersions(regionId, advTypeId);

        System.out.println("\n"+infoVersions);
        if (!CollectionUtils.isEmpty(infoVersions)) {
            infoVersions.sort(Comparator.comparingInt(InfoVersion::getVersion));
        }
        Collections.reverse(infoVersions);
        Integer version = infoVersions.get(0).getVersion();
        return version;
    }

    private List<InfoVersion> getInfoVersions(Integer regionId, Long advTypeId) {
        //查出对应区域的所有广告ID
        List<Long> list = infoRegionService.selectAdvByRegion(regionId);
        System.out.println(list);
        //查出所有开机广告
        List<AdvInfo> advInfoList = advInfoService.getAdvInfoByRegionIdAndAdvTypeId(list, advTypeId);
        System.out.println(advInfoList);
        //获取广告的版本信息
        return getInfoVersions(advInfoList);
    }

    /**
     * 根据区域信息和广告类型以及版本号确定最新开机广告版本信息
     * @param regionId
     * @param advTypeId
     * @param version
     * @return
     */
    @Override
    public AdvInfo getAdvInfoByRegionIdAndAdvTypeIdAndVersion(Integer regionId, Long advTypeId, Integer version) {
        List<InfoVersion> infoVersions = getInfoVersions(regionId, advTypeId);
        if (!CollectionUtils.isEmpty(infoVersions)) {
            infoVersions.stream().filter((p) -> p.getVersion() == version);
        }
        if (!CollectionUtils.isEmpty(infoVersions)) {
            AdvInfo advInfo = advInfoService.findById(infoVersions.get(0).getAdvInfoId());
            return advInfo;
        }
        return null;
    }





    /**
     * 查询某个区域对应的所有开机广告版本信息
     * @param advInfos
     * @return
     */
    @Override
    public List<InfoVersion> getInfoVersions(List<AdvInfo> advInfos) {
        List<InfoVersion> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(advInfos)) {
            advInfos.forEach(advInfo -> {
                if(advInfo != null){
                    InfoVersion infoVersion = getInfoVersionByAdvInfoId(advInfo.getId());
                    if (infoVersion != null) {
                        list.add(infoVersion);
                    }
                }
            });
        }
        return list;
    }

    @Override
    public Integer getUpToDateVersionNumByRegion(Integer regionId, Long advTypeId) {
        return null;
    }

    /**
     * 创建开机广告，保存初始版本号位 0，若有新的版本号出现，则递增
     *
     * @param advInfoId
     * @param versionNum
     */
    @Override
    public void saveVersion(Long advInfoId, Integer versionNum) {
        InfoVersion infoVersion = new InfoVersion();
        infoVersion.setAdvInfoId(advInfoId);
        infoVersion.setVersion(versionNum);
        save(infoVersion);
    }

    /**
     * 更新广告信息
     *
     * @param infoVersion
     */
    @Override
    public void updateVersion(InfoVersion infoVersion) {
        if (infoVersion != null) {
            updateByPrimaryKeySelective(infoVersion);
        }
    }

    /**
     * 根据广告ID 批量删除对应版本信息
     *
     * @param advInfoIds
     */
    @Override
    public void deleteByAdvInfoIds(List<Long> advInfoIds,List<Integer> versionNums) {
        InfoVersionExample example = new InfoVersionExample();
        InfoVersionExample.Criteria criteria = example.createCriteria();
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            criteria.andAdvInfoIdIn(advInfoIds);
        }
        if (!CollectionUtils.isEmpty(versionNums)) {
            criteria.andVersionIn(versionNums);
        }
        deleteByExample(example);
    }


    /**
     * 根据广告ID 或 版本号删除广告版本对应信息
     * @param advInfoId
     * @param versionNum
     */
    @Override
    public void deleteByAdvInfoIdAndVersionNum(Long advInfoId, Integer versionNum) {
        InfoVersionExample example = new InfoVersionExample();
         InfoVersionExample.Criteria criteria =  example.createCriteria();
        if (advInfoId != null) {
            criteria.andAdvInfoIdEqualTo(advInfoId);
        }
        if (versionNum != null) {
            criteria.andVersionEqualTo(versionNum);
        }
        deleteByExample(example);
    }

    /**
     * 根据广告ID 查询对应版本信息
     *
     * @return
     */
    @Override
    public List<InfoVersion> selectByAdvInfoId(Long advInfoId,Integer versionNum) {
        InfoVersionExample example = new InfoVersionExample();
        InfoVersionExample.Criteria criteria = example.createCriteria();
        if (advInfoId != null) {
            criteria.andAdvInfoIdEqualTo(advInfoId);
        }
        if (versionNum != null) {
            criteria.andVersionEqualTo(versionNum);
        }
        List<InfoVersion> list = selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return null;
    }



}
