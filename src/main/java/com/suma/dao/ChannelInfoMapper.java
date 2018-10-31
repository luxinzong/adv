package com.suma.dao;

import com.suma.pojo.ChannelInfo;
import com.suma.pojo.ChannelInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ChannelInfoMapper extends BaseDAO<ChannelInfo, ChannelInfoExample, Long> {
    long countByExample(ChannelInfoExample example);

    int deleteByExample(ChannelInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    List<ChannelInfo> selectByExample(ChannelInfoExample example);

    ChannelInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    int updateByExample(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);

    List<String> selectChannelIds();
}