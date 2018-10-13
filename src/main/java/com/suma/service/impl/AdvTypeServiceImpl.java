package com.suma.service.impl;

import com.suma.dao.AdvTypeMapper;
import com.suma.pojo.AdvType;
import com.suma.service.AdvTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luxinzong
 */

@Service
public class AdvTypeServiceImpl implements AdvTypeService {

    @Autowired
    AdvTypeMapper advTypeMapper;

    @Override
    public void deleteByAdvTypeId(Long id) {
        advTypeMapper.deleteByAdvTypeId(id);
    }

    @Override
    public void insertAdvType(AdvType advType) {
        advTypeMapper.insertAdvType(advType);
    }

    @Override
    public AdvType selectByAdvTypeId(Long id) {
        return advTypeMapper.selectByAdvTypeId(id);
    }

    @Override
    public List<AdvType> selectAdvTypes() {
        return advTypeMapper.selectAdvTypes();
    }

    @Override
    public List<AdvType> selectAdvTypesByType(String advType) {
        return advTypeMapper.selectAdvTypesByType(advType);
    }

    @Override
    public void updateAdvType(AdvType advType) {
        advTypeMapper.updateAdvType(advType);
    }
}
