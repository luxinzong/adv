package com.suma.service;

import com.suma.pojo.AdvInfoServiceGroup;
import com.suma.pojo.AdvInfoServiceGroupExample;
import com.suma.vo.AdvInfoVO;
import com.suma.pojo.ServiceGroup;
import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/29 0029
 * @description
 */
public interface AdvInfoServiceGroupService extends BaseService<AdvInfoServiceGroup,AdvInfoServiceGroupExample,Long> {
    void deleteAdvRegionByAdvInfoId(List<Long> list);
    void deleteAdvRegionByAdvInfoId(Long id);
    void saveServiceInfomation(AdvInfoVO advInfoVO, Long advInfoId);
    void updateServiceGroup(AdvInfoVO advInfoVO);
    List<ServiceGroup> findGroupNamesByAdvId(Long advInfoId);
    void getServiceGroup(AdvInfoVO advInfoVO);
}

