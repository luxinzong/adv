package com.suma.controller;

import com.suma.constants.ExceptionConstants;
import com.suma.exception.MenuException;
import com.suma.pojo.AdvMenu;
import com.suma.service.iAdvMenuService;
import com.suma.utils.Result;
import com.suma.vo.AdvMenuVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description 菜单管理controller
 **/
@RestController
@RequestMapping("/system/menu")
public class AdvMenuController extends BaseController{

    @Autowired
    private iAdvMenuService advMenuService;

    /**
     * 新增菜单
     *
     * @return
     */
    @PostMapping("/add")
    public Result addAdvMenu(@RequestBody @Validated AdvMenuVO advMenuVO){
        AdvMenu advMenu = new AdvMenu();
        BeanUtils.copyProperties(advMenuVO,advMenu);

        return toResult(advMenuService.insertAdvMenu(advMenu));
    }

    /**
     * 删除菜单信息
     *
     * @param paramMap
     * @return
     */
    @PostMapping("/delete")
    public Result removeAdvMenu(@RequestBody Map<String,Object> paramMap){
        Integer menuId = (Integer)paramMap.get("menuId");
        if(menuId == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_ISNULL);
        }
        if(advMenuService.selectAdvMenuCountByParentId(menuId) > 0){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_EXIST_NEXT_DEPT);
        }
        //todo 检测是否有角色绑定菜单

        return toResult(advMenuService.deleteMenuById(menuId));
    }

    /**
     * 修改菜单信息
     */
    @PostMapping("/update")
    public Result updateAdvMenuDept(@Validated AdvMenuVO advMenuVO,
                                    @Param("menuId")Integer menuId){
        if(menuId == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_ISNULL);
        }
        AdvMenu advMenu = new AdvMenu();
        BeanUtils.copyProperties(advMenuVO,advMenu);
        advMenu.setMenuId(menuId);

        return toResult(advMenuService.updateAdvMenu(advMenu));
    }

}
