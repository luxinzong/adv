package com.suma.service;

import com.suma.dto.AdvMenuDto;
import com.suma.dto.AdvPermsDto;
import com.suma.pojo.AdvMenu;

import java.util.List;
import java.util.Set;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description 菜单 业务层
 **/
public interface iAdvMenuService {


    public int insertAdvMenu(AdvMenu advMenu);

    public int updateAdvMenu(AdvMenu advMenu);

    public int deleteMenuById(Integer menuId);

    public List<AdvMenuDto> selectMenuAll();

    public List<AdvMenuDto> selectMenuTree();

    public Set<String> selectMenuPermsByUserId(Integer userId);

    public List<AdvMenuDto> selectAdvMenuList(AdvMenu advMenu);

    public List<AdvMenuDto> selectMenuTreeStatusIsValid();

    public boolean checkRoleInMenuByMenuId(Integer menuId);

    public int selectAdvMenuCountByParentId(Integer parentId);

    public List<AdvPermsDto>  selectMenuTreeByUserId(Integer userId);






}
