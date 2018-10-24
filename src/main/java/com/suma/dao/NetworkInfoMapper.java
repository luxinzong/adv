package com.suma.dao;

import com.suma.pojo.NetworkInfo;
import com.suma.pojo.NetworkInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetworkInfoMapper extends BaseDAO<NetworkInfo, NetworkInfoExample, Long>{
    long countByExample(NetworkInfoExample example);

    int deleteByExample(NetworkInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NetworkInfo record);

    int insertSelective(NetworkInfo record);

    List<NetworkInfo> selectByExample(NetworkInfoExample example);

    NetworkInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NetworkInfo record, @Param("example") NetworkInfoExample example);

    int updateByExample(@Param("record") NetworkInfo record, @Param("example") NetworkInfoExample example);

    int updateByPrimaryKeySelective(NetworkInfo record);

    int updateByPrimaryKey(NetworkInfo record);
}