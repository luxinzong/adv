package com.suma.dao;

import com.suma.pojo.AdvRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(AdvRole record);

    int insertSelective(AdvRole record);

    AdvRole selectByPrimaryKey(Integer roleId);

    AdvRole selectByAdvRoleName(@Param("roleName") String roleName);

    List<AdvRole> selectRoleList(@Param("roleName")String roleName,@Param("roleKey")String roleKey,
                                    @Param("status")String status,@Param("startTime")String startTime,
                                    @Param("endTime")String endTime);

    int selectMaxRoleSort();

    int updateByPrimaryKeySelective(AdvRole record);

    int updateByPrimaryKey(AdvRole record);

}