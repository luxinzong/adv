package com.suma.dao;

import com.suma.pojo.ServiceGroupExample;
import com.suma.pojo.ServiceInfoGroup;
import com.suma.pojo.ServiceInfoGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceInfoGroupMapper extends BaseDAO<ServiceInfoGroup, ServiceInfoGroupExample, Long>{
    long countByExample(ServiceInfoGroupExample example);

    int deleteByExample(ServiceInfoGroupExample example);

    int insert(ServiceInfoGroup record);

    int insertSelective(ServiceInfoGroup record);

    List<ServiceInfoGroup> selectByExample(ServiceInfoGroupExample example);

    int updateByExampleSelective(@Param("record") ServiceInfoGroup record, @Param("example") ServiceInfoGroupExample example);

    int updateByExample(@Param("record") ServiceInfoGroup record, @Param("example") ServiceInfoGroupExample example);
}