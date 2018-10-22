package com.suma.dao;

import com.suma.pojo.AdvUserRole;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description
 **/
public interface AdvUseRoleMapper {

    /**
     * 添加用户对应角色信息
     *
     * @param userRoleList
     * @return
     */
    public int batchUserRole(List<AdvUserRole> userRoleList);

    /**
     * 通过userId查询对应roleIds
     *
     * @param userId
     * @return
     */
    public List<Integer> selectRoleIdsByUserId(Integer userId);

    /**
     * 根据userId删除表信息
     *
     * @param userId
     * @return
     */
    public int deleteRoleIdsByUserId(Integer userId);

}
