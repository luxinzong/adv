package com.suma.service;

import com.suma.pojo.ServiceGroup;
import com.suma.pojo.ServiceGroupExample;
import com.suma.vo.ServiceGroupVO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
public interface ServiceGroupService extends BaseService<ServiceGroup, ServiceGroupExample, Long> {

    void save(ServiceGroup serviceGroup, List<String> serviceNames);

    List<ServiceGroup> findByName(String groupName);

    void update(ServiceGroup serviceGroup, List<String> serviceNames);
}
