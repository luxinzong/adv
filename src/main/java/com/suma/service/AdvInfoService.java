package com.suma.service;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.pojo.AdvItem;
import com.suma.pojo.AdvMaterial;
import com.suma.vo.AdvPutVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

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

    List<AdvInfo> getAdvInfoByAdvInfoIdIdAndAdvTypeId(List<Long> advInfoIds,Long advTypeId);

    List<AdvInfo> selectAdvInfoByNameAndStatusAndOthor(Integer status, String name, String startDate, String endDate, String advTypeId);

    int deleteAdvRelationInfo(List<Long> advInfoIds);

    int deleteAdvLocationByAdvInfoIds(List<Long> advInfoId);

    void setBootLocation(AdvInfo advInfo);

    List<AdvInfo> getPuttingAdv(AdvPutVO advPutVO);

    List<AdvInfo> selectAdvInfoByDate(Map<String, Object> map);

    List<AdvInfo> getAdvByIds(List<Long> ids);

    void getMaterials(Long advTypeId, List<AdvItem> list, List<AdvInfo> advInfoList);
    void setAdvLocation(AdvInfo advInfo, AdvItem advItem);

    void setAdvMapByOne(Map<AdvItem, MultipartFile> map, AdvInfo advInfo);

    Integer getAdvInfoVersionByAdvTypeId(Long advTypeId);



}
