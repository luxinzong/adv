package com.suma.dao;

import com.suma.pojo.MaterialType;
import com.suma.pojo.MaterialTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialTypeMapper {
    long countByExample(MaterialTypeExample example);

    int deleteByExample(MaterialTypeExample example);

    int insert(MaterialType record);

    int insertSelective(MaterialType record);

    List<MaterialType> selectByExample(MaterialTypeExample example);

    int updateByExampleSelective(@Param("record") MaterialType record, @Param("example") MaterialTypeExample example);

    int updateByExample(@Param("record") MaterialType record, @Param("example") MaterialTypeExample example);
}