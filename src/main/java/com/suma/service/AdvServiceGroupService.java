package com.suma.service;

import com.suma.pojo.*;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/24
 * @description:
 */
public interface AdvServiceGroupService extends BaseService<AdvInfoServiceGroup, AdvInfoServiceGroupExample, Long> {
    List<AdvInfo> findAdvByGroups(List<ServiceGroup> groups, AdvType advType);

    String findChannelIdsByAdvId(Long id);
}
