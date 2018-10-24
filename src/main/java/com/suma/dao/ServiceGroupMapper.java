package com.suma.dao;

import com.suma.pojo.ServiceGroup;
import com.suma.pojo.ServiceGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceGroupMapper extends BaseDAO<ServiceGroup, ServiceGroupExample, Long>{
    long countByExample(ServiceGroupExample example);

    int deleteByExample(ServiceGroupExample example);

    int deleteByPrimaryKey(Long sgid);

    int insert(ServiceGroup record);

    int insertSelective(ServiceGroup record);

    List<ServiceGroup> selectByExample(ServiceGroupExample example);

    ServiceGroup selectByPrimaryKey(Long sgid);

    int updateByExampleSelective(@Param("record") ServiceGroup record, @Param("example") ServiceGroupExample example);

    int updateByExample(@Param("record") ServiceGroup record, @Param("example") ServiceGroupExample example);

    int updateByPrimaryKeySelective(ServiceGroup record);

    int updateByPrimaryKey(ServiceGroup record);
}