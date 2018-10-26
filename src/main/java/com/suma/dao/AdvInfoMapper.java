package com.suma.dao;

import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AdvInfoMapper extends BaseDAO<AdvInfo,AdvInfoExample,Long>{

    AdvInfo findById(@Param("id") Long id);
    List<AdvInfo> selectAdvInfo(@Param("map") Map<String,Object> map);

}