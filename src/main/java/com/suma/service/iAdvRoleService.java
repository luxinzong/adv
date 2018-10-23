package com.suma.service;

import com.github.pagehelper.PageInfo;
import com.suma.dto.AdvRoleDto;
import com.suma.pojo.AdvRole;

import java.util.List;
import java.util.Set;

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

    /**
     * 查询角色列表
     *
     * @param roleName
     * @param roleKey
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
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
     * 批量删除角色
     *
     * @param advRoleIds
     * @return
     */
    public int deleteRoleByIds(List<Integer> advRoleIds);

    /**
     * 通过id查询角色信息
     *
     * @param advRoleId
     * @return
     */
    public AdvRole selectRoleById(Integer advRoleId);

    /**
     * 根据用户id查询对应角色id
     *
     * @param userId
     * @return
     */
    public Set<String> selectRoleKeys(Integer userId);





}
