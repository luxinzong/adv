package com.suma.dao;

import com.suma.pojo.AdvType;

public interface AdvTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdvType record);

    int insertSelective(AdvType record);

    AdvType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdvType record);

    int updateByPrimaryKey(AdvType record);
}