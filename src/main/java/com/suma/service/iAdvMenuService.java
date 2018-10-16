package com.suma.service;

import com.suma.pojo.AdvMenu;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description 菜单 业务层
 **/
public interface iAdvMenuService {


    public int insertAdvMenu(AdvMenu advMenu);

    public int updateAdvMenu(AdvMenu advMenu);

    public int deleteMenuById(Integer menuId);

    public List<AdvMenu> selectMenuAll();

    public int selectAdvMenuCountByParentId(Integer parentId);




}
