package com.suma.dao;

import com.suma.pojo.AdvCheckDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvCheckDetailMapper {

    int deleteById(@Param("id") Long id);

    int insert(AdvCheckDetail advCheckDetail);

    AdvCheckDetail select(@Param("advInfoId") Long advInfoId);

    int updateById(AdvCheckDetail advCheckDetail);

    List<AdvCheckDetail> selectAll();

    int deleteAll(Long[] ids);

    List<AdvCheckDetail> selectAll(@Param("status") Integer status);
}