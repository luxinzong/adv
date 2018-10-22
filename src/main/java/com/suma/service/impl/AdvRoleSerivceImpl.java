package com.suma.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvRoleMapper;
import com.suma.dao.AdvRoleMenuMapper;
import com.suma.exception.RoleException;
import com.suma.pojo.AdvRole;
import com.suma.pojo.AdvRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suma.service.iAdvRoleService;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/18 0018
 * @Description
 **/
@Service
public class AdvRoleSerivceImpl implements iAdvRoleService {

    @Autowired
    private AdvRoleMapper advRoleMapper;
    @Autowired
    private AdvRoleMenuMapper advRoleMenuMapper;

    @Override
    public List<AdvRole> selectRoleAll() {
        return null;
    }

    /**
     * 通过参数查询角色列表
     *
     * @param roleName
     * @param roleKey
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<AdvRole> selectRoleList(String roleName, String roleKey, String status, String startTime, String endTime) {
        List<AdvRole> roleList = advRoleMapper.selectRoleList(roleName,roleKey,status,startTime,endTime);
        //将查询出来的roleList添加对应的menuId
        if(!CollectionUtils.isEmpty(roleList)){
            addMenuIdForAdvRole(roleList);
        }
        PageInfo<AdvRole> pageInfo = new PageInfo<>(roleList);

        return pageInfo;
    }

    /**
     * 给对应角色添加对应menuId
     *
     * @param advRoleList
     */
    private void addMenuIdForAdvRole(List<AdvRole> advRoleList){
        advRoleList.forEach(advRole -> {
            List<Integer> menuIds = advRoleMenuMapper.selectMenuIdsByAdvRoleId(advRole.getRoleId());
            advRole.setMenuIds(menuIds);
        });
    }

    /**
     * 新增保存角色信息
     *
     * @param advRole
     * @return
     */
    @Override
    public int insertRole(AdvRole advRole) {
        //检查角色名称是否已经存在
        String roleName = advRole.getRoleName();
        AdvRole checkAdvRole = advRoleMapper.selectByAdvRoleName(roleName);
        if(checkAdvRole != null){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_EXIST_NAME);
        }
        //todo 添加创建人
        //新增角色信息
        Integer roleSort = advRole.getRoleSort();
        if(roleSort == null){
            int currentMaxRoleSort = advRoleMapper.selectMaxRoleSort();
            int resultRoleSort = currentMaxRoleSort + 1;
            advRole.setRoleSort(resultRoleSort);
        }

        int insertRows = advRoleMapper.insertSelective(advRole);
        //提交数据后获取对应存入对应角色id，以便增加对应菜单id
        AdvRole tempAdvRole = advRoleMapper.selectByAdvRoleName(roleName);
        advRole.setRoleId(tempAdvRole.getRoleId());
        int insertAdcRoleMenuRows = insertAdvRoleMenu(advRole);
        return insertRows + insertAdcRoleMenuRows;
    }

    /**
     * 新增角色菜单信息
     *
     * @return AdvRole 角色对象
     */
    private int insertAdvRoleMenu(AdvRole advRole){
        int rows = 0;
        //新增角色和菜单管理
        List<AdvRoleMenu> advRoleMenuList = Lists.newArrayList();
        advRole.getMenuIds().forEach(advMenuId -> {
            AdvRoleMenu advRoleMenu = new AdvRoleMenu();
            advRoleMenu.setRoleId(advRole.getRoleId());
            advRoleMenu.setMenuId(advMenuId);
            advRoleMenuList.add(advRoleMenu);
        });
        //进行添加操作
        if(advRoleMenuList.size() > 0){
            rows = advRoleMenuMapper.batchAdvRoleMenu(advRoleMenuList);
        }

        return rows;
    }

    /**
     * 修改角色信息
     *
     * @param advRole
     * @return
     */
    @Override
    public int updateRole(AdvRole advRole) {
        //先判断当前角色是否存在
        AdvRole selectAdvRole = advRoleMapper.selectByPrimaryKey(advRole.getRoleId());
        if(selectAdvRole == null){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_ID_NOT_EXIST);
        }
        //对信息进行修改
        int updateRows = advRoleMapper.updateByPrimaryKeySelective(advRole);
        //对应菜单进行修改,先删除后添加
        int deleteRows = advRoleMenuMapper.deleteAdvRoleMenuByAdvRoleId(advRole.getRoleId());
        //说明删除了数据,则需要进行添加，否则不需要添加
        int insertRows = 0;
        if(deleteRows > 0){
            insertRows = insertAdvRoleMenu(advRole);
        }
        //返回影响表的行数
        return updateRows + insertRows + deleteRows;
    }

    /**
     * 删除角色信息
     *
     * @param advRoleId
     * @return
     */
    @Override
    public int deleteRoleById(Integer advRoleId) {
        //先判断删除角色的id是否存在
        AdvRole advRole = advRoleMapper.selectByPrimaryKey(advRoleId);
        if(advRole == null){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_ID_NOT_EXIST);
        }
        //删除对应role信息
        int rows = advRoleMapper.deleteByPrimaryKey(advRoleId);
        int deleteMenuIdsRows = deleteMenuIdsBYAdvRoleId(rows);

        return rows + deleteMenuIdsRows;
    }

    /**
     * 根据角色id删除对应菜单id
     *
     * @param roleId
     * @return
     */
    private int deleteMenuIdsBYAdvRoleId(Integer roleId){
        return advRoleMenuMapper.deleteAdvRoleMenuByAdvRoleId(roleId);
    }

    @Override
    public AdvRole selectRoleById(Integer advRoleId) {
        return null;
    }
}
