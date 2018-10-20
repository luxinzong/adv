package com.suma.dao;

import com.suma.pojo.AdvCheckDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvCheckDetailMapper {

    int deleteById(@Param("id") Long id);

    int insert(AdvCheckDetail advCheckDetail);

    AdvCheckDetail select(@Param("id") Long id);

    int updateById(AdvCheckDetail advCheckDetail);

    List<AdvCheckDetail> selectAll();

    int deleteAll(Long[] ids);
}