package com.suma.dao;

import com.suma.pojo.AdvCheckDetail;
import com.suma.pojo.AdvCheckDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvCheckDetailMapper {
    int countByExample(AdvCheckDetailExample example);

    int deleteByExample(AdvCheckDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvCheckDetail record);

    int insertSelective(AdvCheckDetail record);

    List<AdvCheckDetail> selectByExample(AdvCheckDetailExample example);

    AdvCheckDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvCheckDetail record, @Param("example") AdvCheckDetailExample example);

    int updateByExample(@Param("record") AdvCheckDetail record, @Param("example") AdvCheckDetailExample example);

    int updateByPrimaryKeySelective(AdvCheckDetail record);

    int updateByPrimaryKey(AdvCheckDetail record);
}