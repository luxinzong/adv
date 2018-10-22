package com.suma.dao;

import com.suma.pojo.TsInfo;
import com.suma.pojo.TsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsInfoMapper {
    long countByExample(TsInfoExample example);

    int deleteByExample(TsInfoExample example);

    int deleteByPrimaryKey(Long tsId);

    int insert(TsInfo record);

    int insertSelective(TsInfo record);

    List<TsInfo> selectByExample(TsInfoExample example);

    TsInfo selectByPrimaryKey(Long tsId);

    int updateByExampleSelective(@Param("record") TsInfo record, @Param("example") TsInfoExample example);

    int updateByExample(@Param("record") TsInfo record, @Param("example") TsInfoExample example);

    int updateByPrimaryKeySelective(TsInfo record);

    int updateByPrimaryKey(TsInfo record);
}