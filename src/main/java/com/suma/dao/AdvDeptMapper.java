package com.suma.dao;

import com.suma.pojo.AdvDept;

public interface AdvDeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(AdvDept record);

    int insertSelective(AdvDept record);

    AdvDept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(AdvDept record);

    int updateByPrimaryKey(AdvDept record);
}