package com.suma.service.impl;

import com.suma.dao.NetworkInfoMapper;
import com.suma.pojo.NetworkInfo;
import com.suma.pojo.NetworkInfoExample;
import com.suma.service.BaseService;
import com.suma.service.NetworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/18
 * @description:
 */
@Service
public class NetworkServiceImpl extends BaseServiceImpl<NetworkInfo, NetworkInfoExample, Long> implements NetworkService {
    @Resource
    public void setBaseDao(NetworkInfoMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Override
    public Long findPKByNetworkId(Long networkId) {
        NetworkInfoExample example = new NetworkInfoExample();
        example.createCriteria().andNetworkIdEqualTo(networkId);
        List<NetworkInfo> networkInfos = selectByExample(example);
        if (networkInfos.size() > 0) {
            return networkInfos.get(0).getId();
        }
        return null;
    }

    @Override
    public Long findNetworkIdByPk(Long nid) {
        NetworkInfo networkInfo = findByPK(nid);
        if (networkInfo != null)
            return networkInfo.getNetworkId();
        return null;
    }
}
