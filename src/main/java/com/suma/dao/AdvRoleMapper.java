package com.suma.dao;

import com.suma.pojo.AdvRole;

public interface AdvRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AdvRole record);

    int insertSelective(AdvRole record);

    AdvRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(AdvRole record);

    int updateByPrimaryKey(AdvRole record);
}