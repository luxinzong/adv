package com.suma.dao;

import com.suma.pojo.AdvUser;

public interface AdvUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(AdvUser record);

    int insertSelective(AdvUser record);

    AdvUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(AdvUser record);

    int updateByPrimaryKey(AdvUser record);
}