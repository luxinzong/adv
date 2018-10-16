package com.suma.service.impl;

import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvMenuMapper;
import com.suma.exception.MenuException;
import com.suma.pojo.AdvMenu;
import com.suma.service.iAdvMenuService;
import com.suma.utils.AncestorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description
 **/
@Service
public class AdvMenuServiceImpl implements iAdvMenuService {

    @Autowired
    private AdvMenuMapper advMenuMapper;

    /**
     * 添加部门
     *
     * @param advMenu
     * @return
     */
    @Override
    public int insertAdvMenu(AdvMenu advMenu) {
        //判断菜单名称是否存在
        String menuName = advMenu.getMenuName();
        int result = advMenuMapper.checkAdvMenuUnique(menuName);
        if(result > 0){
             throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_EXIST_NAME);
        }
        //设置orderNum，如果为空的话取当前数据库中最大值+1
        Integer orderNum = advMenu.getOrderNum();
        if(orderNum == null){
            Integer parentId = advMenu.getParentId();
            //如果当前parentId为空，说明是一级菜单,直接查询parentId=0的最大orderNum
            if(parentId == null){
                parentId = 0;
            }

            int currentMaxOrderNum = advMenuMapper.getMaxAdvMenuOrderNum(parentId);
            int newOrderNum = currentMaxOrderNum + 1;
            advMenu.setOrderNum(newOrderNum);
        }
        //查询父类部门
        AdvMenu parentAdvMenu = advMenuMapper.selectByPrimaryKey(advMenu.getParentId());
        if(parentAdvMenu != null){
            //添加祖先部门ID
            advMenu.setAncestors(parentAdvMenu.getAncestors() + "," + advMenu.getParentId());
        }else{//当前部门没有父亲部门
            advMenu.setAncestors(AncestorUtil.ROOT);
        }
        //默认状态为显示
        advMenu.setVisible(CommonConstants.NORMAL_STATUS);
        //todo 登录工具添加用户名
        return advMenuMapper.insertSelective(advMenu);
    }

    /**
     * 修改部门信息
     *
     * @param advMenu
     * @return
     */
    @Override
    public int updateAdvMenu(AdvMenu advMenu) {
        //判断菜单id存在
        int advMenuId = advMenu.getMenuId();
        AdvMenu selectAdvMenu = advMenuMapper.selectByPrimaryKey(advMenuId);
        if(selectAdvMenu == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_NOT_EXIST);
        }
        //修改对应祖先对应数据
        AdvMenu parentAdvMenu = advMenuMapper.selectByPrimaryKey(advMenu.getParentId());
        if(parentAdvMenu == null){
            advMenu.setAncestors(AncestorUtil.ROOT);
        }else{
            advMenu.setAncestors(parentAdvMenu.getAncestors() + "," +advMenu.getParentId());
        }
        //todo 添加修改用户名
        return advMenuMapper.updateByPrimaryKeySelective(advMenu);
    }

    @Override
    public int selectAdvMenuCountByParentId(Integer parentId) {
        return advMenuMapper.selectAdvMenuCountByParentId(parentId);
    }

    @Override
    public int deleteMenuById(Integer menuId) {
        //判断当前id是否存在
        AdvMenu selectAdvMenu = advMenuMapper.selectByPrimaryKey(menuId);
        if(selectAdvMenu == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_NOT_EXIST);
        }
        return advMenuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public List<AdvMenu> selectMenuAll() {
        return advMenuMapper.selectAdvMenuAll();
    }
}
