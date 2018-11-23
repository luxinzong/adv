package com.suma.service;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.pojo.InfoVersion;
import com.suma.pojo.InfoVersionExample;

import java.util.List;
import java.util.Set;

/**
 * @author: luxinzong
 * @date: 2018/11/12 0012
 * @description
 */
public interface InfoVersionService extends BaseService<InfoVersion,InfoVersionExample,Long> {

    void saveVersion(Long advInfoId, Integer versionNum);
    void updateVersion(InfoVersion infoVersion);

    void deleteByAdvInfoIds(List<Long> advInfoIds, List<Integer> versionNums);
    void deleteByAdvInfoIdAndVersionNum(Long advInfoId, Integer versionNum);

    Integer getUpToDateVersionNumByRegion(Integer regionId,Long advTypeId);
    List<InfoVersion> selectByAdvInfoId(Long advInfoId,Integer versionNum);

    List<InfoVersion> getInfoVersions(List<AdvInfo> advInfos);

    InfoVersion getInfoVersionByAdvInfoId(Long advInfoId);

    Integer getUpToDateVersion(Integer regionId, Long advTypeId);

    AdvInfo getAdvInfoByRegionIdAndAdvTypeIdAndVersion(Integer regionId, Long advTypeId,Integer version);

    List<AdvInfo> getAdvInfoByRegionIdAndAdvTypeId(Integer regionId,Long advTypeId);

    List<Long> getAdvPuttingByVersion(List<Long> ids, Integer version);

    Integer getAdvByRegionAndAdvTypeIdAndStatus(Integer region, String advTypeId, Integer status);

    AdvInfo getUpToDateAdv(List<AdvInfo> list);

}
