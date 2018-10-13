package com.suma.service.impl;

import com.suma.dao.AdvInfoMapper;
import com.suma.pojo.AdvInfo;
import com.suma.service.AdvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author luxinzong
 */

@Service
public class AdvInfoServiceImpl implements AdvInfoService {
    @Autowired
    AdvInfoMapper advInfoMapper;

    @Override
    public void deleteByAdvInfoId(Long id) {
        advInfoMapper.deleteByAdvInfoId(id);
    }

    @Override
    public void insertAdvInfo(AdvInfo advInf) {
        advInfoMapper.insertAdvInfo(advInf);
    }

    @Override
    public AdvInfo selectByAdvInfoId(Long id) {
        return advInfoMapper.selectByAdvInfoId(id);
    }

    @Override
    public List<AdvInfo> selectAdvInfos() {
        return advInfoMapper.selectAdvInfos();
    }

    @Override
    public List<AdvInfo> selectByNameAndStatusAndDate(String name, Integer status, Date startDate, Date endDate) {
        return advInfoMapper.selectByNameAndStatusAndDate(name, status, startDate, endDate);
    }

    @Override
    public void updateAdvInfo(AdvInfo advInfo) {
        advInfoMapper.updateAdvInfo(advInfo);
    }
}
