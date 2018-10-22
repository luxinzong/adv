package com.suma.dao;

import com.suma.pojo.AdvRoleMenu;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/18 0018
 * @Description 角色与菜单关联表 数据层
 **/
public interface AdvRoleMenuMapper {


    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param advRoleId
     * @return
     */
    public int deleteAdvRoleMenuByAdvRoleId(Integer advRoleId);

    /**
     * 批量删除角色菜单关联信息
     *
     * @param ids
     * @return
     */
    public int delteAdvRoleMenu(List<Integer> ids);



    /**
     * 查询菜单使用数量
     *
     * @param advMenuId
     * @return
     */
    public int selectCountRoleMenuByMenuId(Integer advMenuId);


    /**
     * 批量新增角色菜单信息
     *
     * @param advRoleMenuList
     * @return
     */
    public int batchAdvRoleMenu(List<AdvRoleMenu> advRoleMenuList);

    /**
     * 通过角色id查询对应菜单id
     *
     * @param roleId
     * @return
     */
    public List<Integer> selectMenuIdsByAdvRoleId(Integer roleId);



}
