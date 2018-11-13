package com.suma.service;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.pojo.AdvMaterial;

import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
public interface AdvInfoService extends BaseService<AdvInfo, AdvInfoExample, Long> {
    List<AdvInfo> selectAdvInfo(Map<String, Object> map);

    AdvInfo findById(Long id);

    List<AdvMaterial> getVedioMaterial();

    void deleteByAdvInfoIds(List<Long> list);

    void judgeAdvInfo(AdvInfo advInfo);

    List<AdvInfo> getAdvInfoByRegionIdAndAdvTypeId(List<Long> advInfoIds,Long advTypeId);

    List<AdvInfo> selectAdvInfoByNameAndStatusAndOthor(Integer status, String name, String startDate, String endDate,
                                                       Integer pageNum, Integer pageSize, String advTypeId);

}
