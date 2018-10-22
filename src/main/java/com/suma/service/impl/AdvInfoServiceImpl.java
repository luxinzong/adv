package com.suma.service.impl;

import com.suma.dao.AdvInfoMapper;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.service.AdvInfoService;
import com.suma.vo.AdvInfoQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class AdvInfoServiceImpl extends BaseServiceImpl<AdvInfo, AdvInfoExample, Long> implements AdvInfoService {

    @Autowired
    AdvInfoMapper advInfoMapper;

    @Resource
    public void setBaseDao(AdvInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public List<AdvInfo> selectAdvInfo(Map<String,Object> map) {
        return advInfoMapper.selectAdvInfo(map);
    }

    @Override
    public AdvInfo findById(Long id) {
        return advInfoMapper.findById(id);
    }
}
