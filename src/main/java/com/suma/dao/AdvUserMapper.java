package com.suma.dao;

import com.suma.pojo.AdvUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int batchDeleteUser(List<Integer> userIds);

    List<AdvUser> selectUserList(@Param("userName") String userName,@Param("phoneNumber") String phoneNumber,@Param("status") String status,
                                 @Param("startTime") String startTime,@Param("endTime") String endTime);

    AdvUser selectAdvUserByUserName(@Param("userName") String username);

    int insert(AdvUser record);

    int insertSelective(AdvUser record);

    AdvUser selectByPrimaryKey(Integer userId);

    int selectUserCount();

    int updateByPrimaryKeySelective(AdvUser record);

    int updateByPrimaryKey(AdvUser record);
}