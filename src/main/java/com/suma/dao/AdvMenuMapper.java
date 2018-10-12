package com.suma.dao;

import com.suma.pojo.AdvMenu;

public interface AdvMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(AdvMenu record);

    int insertSelective(AdvMenu record);

    AdvMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(AdvMenu record);

    int updateByPrimaryKey(AdvMenu record);
}