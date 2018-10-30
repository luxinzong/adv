package com.suma.dao;

import com.suma.pojo.AdvInfoServiceGroup;
import com.suma.pojo.AdvInfoServiceGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdvInfoServiceGroupMapper extends BaseDAO<AdvInfoServiceGroup, AdvInfoServiceGroupExample, Long> {
    long countByExample(AdvInfoServiceGroupExample example);

    int deleteByExample(AdvInfoServiceGroupExample example);

    int insert(AdvInfoServiceGroup record);

    int insertSelective(AdvInfoServiceGroup record);

    List<AdvInfoServiceGroup> selectByExample(AdvInfoServiceGroupExample example);

    int updateByExampleSelective(@Param("record") AdvInfoServiceGroup record, @Param("example") AdvInfoServiceGroupExample example);

    int updateByExample(@Param("record") AdvInfoServiceGroup record, @Param("example") AdvInfoServiceGroupExample example);
}