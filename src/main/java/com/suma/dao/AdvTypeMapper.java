package com.suma.dao;

import com.suma.pojo.AdvType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvTypeMapper {

    void deleteByAdvTypeId(@Param("id") Long id);

    void insertAdvType(AdvType advType);

    AdvType selectByAdvTypeId(@Param("id") Long id);

    List<AdvType> selectAdvTypes();

    List<AdvType> selectAdvTypesByAdvType(@Param("advType") String advType);

    void updateAdvType(AdvType advType);
}