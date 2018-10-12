package com.suma.dao;

import com.suma.pojo.AdvInfo;

public interface AdvInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdvInfo record);

    int insertSelective(AdvInfo record);

    AdvInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdvInfo record);

    int updateByPrimaryKey(AdvInfo record);
}