package com.suma.service;

import com.github.pagehelper.PageInfo;
import com.suma.dto.AdvRoleDto;
import com.suma.pojo.AdvRole;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/18 0018
 * @Description 角色业务层
 **/
public interface iAdvRoleService {

    /**
     * 查询所有角色
     *
     * @return
     */
    public List<AdvRole> selectRoleAll();


    public PageInfo<AdvRole> selectRoleList(String roleName, String roleKey, String status,
                                   String startTime, String endTime);

    /**
     * 新增保存角色信息
     *
     * @param advRole
     * @return
     */
    public int insertRole(AdvRole advRole);

    /**
     * 修改角色信息
     *
     * @param advRole
     * @return
     */
    public int updateRole(AdvRole advRole);

    /**
     * 删除角色信息
     *
     * @param advRoleId
     * @return
     */
    public int deleteRoleById(Integer advRoleId);

    /**
     * 通过id查询角色信息
     *
     * @param advRoleId
     * @return
     */
    public AdvRole selectRoleById(Integer advRoleId);

}
