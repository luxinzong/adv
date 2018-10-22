package com.suma.dao;

import com.suma.pojo.ServiceInfo;
import com.suma.pojo.ServiceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceInfoMapper extends BaseDAO<ServiceInfo, ServiceInfoExample, Long>{
    long countByExample(ServiceInfoExample example);

    int deleteByExample(ServiceInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ServiceInfo record);

    int insertSelective(ServiceInfo record);

    List<ServiceInfo> selectByExample(ServiceInfoExample example);

    ServiceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ServiceInfo record, @Param("example") ServiceInfoExample example);

    int updateByExample(@Param("record") ServiceInfo record, @Param("example") ServiceInfoExample example);

    int updateByPrimaryKeySelective(ServiceInfo record);

    int updateByPrimaryKey(ServiceInfo record);
}