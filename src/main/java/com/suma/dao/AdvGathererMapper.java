package com.suma.dao;

import com.suma.pojo.AdvGatherer;
import com.suma.pojo.AdvGathererExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvGathererMapper extends BaseDAO<AdvGatherer,AdvGathererExample,Long>{
    long countByExample(AdvGathererExample example);

    int deleteByExample(AdvGathererExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvGatherer record);

    int insertSelective(AdvGatherer record);

    List<AdvGatherer> selectByExample(AdvGathererExample example);

    AdvGatherer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvGatherer record, @Param("example") AdvGathererExample example);

    int updateByExample(@Param("record") AdvGatherer record, @Param("example") AdvGathererExample example);

    int updateByPrimaryKeySelective(AdvGatherer record);

    int updateByPrimaryKey(AdvGatherer record);
}