package com.suma.dao;

import com.suma.pojo.AdvUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(AdvUser record);

    int insertSelective(AdvUser record);

    AdvUser selectByPrimaryKey(Integer userId);

    AdvUser selectAdvUserByUserName(String userName);

    List<AdvUser> selectUserList(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber, @Param("status") String status
                        , @Param("startTime") String startTime, @Param("endTime") String endTime);

    int updateByPrimaryKeySelective(AdvUser record);

    int updateByPrimaryKey(AdvUser record);
}