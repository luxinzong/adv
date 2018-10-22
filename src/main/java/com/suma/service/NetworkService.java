package com.suma.service;

import com.suma.pojo.NetworkInfo;
import com.suma.pojo.NetworkInfoExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/18
 * @description:
 */
public interface NetworkService extends BaseService<NetworkInfo, NetworkInfoExample, Long> {
    Long findPKByNetworkId(Long networkId);

    Long findNetworkIdByPk(Long nid);
}
