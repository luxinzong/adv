package com.suma.service;

import com.suma.pojo.ServiceGroup;
import com.suma.pojo.ServiceGroupExample;
import com.suma.pojo.ServiceInfoGroup;
import com.suma.pojo.ServiceInfoGroupExample;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
public interface ServiceInfoGroupService extends BaseService<ServiceInfoGroup, ServiceInfoGroupExample, Long> {

    List<String> findServicesByGroupId(Long sgId);

    void deleteByGroupId(Long sgid);
}
