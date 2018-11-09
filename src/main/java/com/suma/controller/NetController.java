
package com.suma.controller;

import com.suma.dao.NetworkInfoMapper;
import com.suma.pojo.NetworkInfo;
import com.suma.pojo.Region;
import com.suma.pojo.RegionExample;
import com.suma.utils.Result;
import com.suma.vo.NetVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */

@RestController
@RequestMapping("net")
public class NetController extends BaseController {

    @Autowired
    private NetworkInfoMapper networkInfoMapper;

    @RequestMapping("query")
    public Result queryNet() {
        List<NetworkInfo> networkInfos = networkInfoMapper.selectByExample(null);
        NetVO netVO = new NetVO();
        NetworkInfo networkInfo = null;
        if (networkInfos != null && networkInfos.size() > 0) {
            networkInfo = networkInfos.get(0);
            BeanUtils.copyProperties(networkInfo, netVO);
        }
        return Result.success(netVO);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("edit")
    public Result editNet(NetVO netVO) {
        NetworkInfo networkInfo = new NetworkInfo();
        BeanUtils.copyProperties(netVO, networkInfo);
        if (netVO.getId() == null) {
            networkInfoMapper.deleteByExample(null);
            return toResult(networkInfoMapper.insert(networkInfo));
        } else {
            return toResult(networkInfoMapper.updateByPrimaryKey(networkInfo));
        }
    }

}
