package com.suma.service;

import com.suma.pojo.ServiceInfo;
import com.suma.pojo.ServiceInfoExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/17
 * @description:
 */
public interface ServiceInfoService extends BaseService<ServiceInfo, ServiceInfoExample, Long> {
    ServiceInfoExample.Criteria queryServiceByThreeId(String networkId, String tsId, String serviceId, ServiceInfoExample serviceExample);

    void checkDuplicate(ServiceInfo serviceInfo);
}
