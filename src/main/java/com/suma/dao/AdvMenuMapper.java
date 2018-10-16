package com.suma.dao;

import com.suma.pojo.AdvMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(AdvMenu record);

    int insertSelective(AdvMenu record);

    AdvMenu selectByPrimaryKey(Integer menuId);

    AdvMenu selectByAdvMenuName(@Param("menuName") String menuName);

    int selectAdvMenuCountByParentId(@Param("parentId")Integer parentId);

    List<AdvMenu> selectAdvMenuAll();

    int checkAdvMenuUnique(@Param("menuName") String menuName);

    int updateByPrimaryKeySelective(AdvMenu record);

    int updateByPrimaryKey(AdvMenu record);

    int getMaxAdvMenuOrderNum(@Param("parentId") Integer parentId);

}