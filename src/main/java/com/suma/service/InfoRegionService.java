package com.suma.service;

import com.suma.pojo.InfoRegion;
import com.suma.pojo.InfoRegionExample;
import com.suma.vo.AdvInfoVO;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/11/6 0006
 * @description
 */
public interface InfoRegionService extends BaseService<InfoRegion,InfoRegionExample,Long> {
    List<Long> selectAdvByRegion(Integer regionCode);
    List<String> getRegionNames(Long id);

    List<Integer> getRegionIds(Long advInfoId);
    void  deleteByAdvInfoId(Long advInfoId);

    void deleteByAdvInfoIds(List<Long> advInfoIds);
    void saveInfoRegion(List<Integer> regionIds, Long advInfoId);
}
