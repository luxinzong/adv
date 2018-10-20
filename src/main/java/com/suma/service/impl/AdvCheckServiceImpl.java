package com.suma.service.impl;

import com.suma.dao.AdvCheckDetailMapper;
import com.suma.pojo.AdvCheckDetail;
import com.suma.service.AdvCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luxinzong
 * @date 2018/10/20
 * @description
 */
@Service
public class AdvCheckServiceImpl implements AdvCheckService {

    @Autowired
    AdvCheckDetailMapper advCheckDetailMapper;

    @Override
    public int deleteById(Long id) {
        return advCheckDetailMapper.deleteById(id);
    }

    @Override
    public int deleteAll(Long[] ids) {
        return advCheckDetailMapper.deleteAll(ids);
    }

    @Override
    public int insert(AdvCheckDetail record) {
        return advCheckDetailMapper.insert(record);
    }

    @Override
    public AdvCheckDetail select(Long id) {
        return advCheckDetailMapper.select(id);
    }

    @Override
    public List<AdvCheckDetail> selectAll() {
        return advCheckDetailMapper.selectAll();
    }

    @Override
    public int updateById(AdvCheckDetail record) {
        return advCheckDetailMapper.updateById(record);
    }
}
