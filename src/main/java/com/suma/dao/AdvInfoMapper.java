package com.suma.dao;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvInfoMapper {
    int countByExample(AdvInfoExample example);

    int deleteByExample(AdvInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvInfo record);

    int insertSelective(AdvInfo record);

    List<AdvInfo> selectByExample(AdvInfoExample example);

    AdvInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvInfo record, @Param("example") AdvInfoExample example);

    int updateByExample(@Param("record") AdvInfo record, @Param("example") AdvInfoExample example);

    int updateByPrimaryKeySelective(AdvInfo record);

    int updateByPrimaryKey(AdvInfo record);
}