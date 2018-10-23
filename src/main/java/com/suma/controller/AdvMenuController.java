package com.suma.controller;

import com.suma.constants.ExceptionConstants;
import com.suma.dto.AdvMenuDto;
import com.suma.exception.MenuException;
import com.suma.pojo.AdvMenu;
import com.suma.service.iAdvMenuService;
import com.suma.utils.Result;
import com.suma.vo.AdvMenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if(advMenuService.checkRoleInMenuByMenuId(menuId)){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_EXIST_ROLE_BIND_MENU);
        }

        return toResult(advMenuService.deleteMenuById(menuId));
    }

    /**
     * 修改菜单信息
     */
    @PostMapping("/update")
    public Result updateAdvMenuDept(@RequestBody @Validated AdvMenu advMenu){
        return toResult(advMenuService.updateAdvMenu(advMenu));
    }

    /**
     * 获取全部菜单信息
     *
     * @return
     */
    @GetMapping("/listAll")
    public Result listAllAdvMenu(){
        List<AdvMenuDto> advMenuDtoList = advMenuService.selectMenuAll();
        AdvMenuDto advMenu = new AdvMenuDto();
        //添加一个为无
        advMenu.setMenuId(0);
        advMenu.setMenuName("无");
        advMenuDtoList.add(0,advMenu);
        return Result.success(advMenuDtoList);
    }

    /**
     * 展示部门树
     *
     * @return
     */
    @GetMapping("/list")
    public Result listAdvMenuTree(){
        List<AdvMenuDto> advMenuDtoList = advMenuService.selectMenuTree();
        return Result.success(advMenuDtoList);
    }


    /**
     * 通过条件进行查询
     *
     * @return
     */
    @PostMapping("/query")
    public Result query(@RequestBody Map<String,Object> requestParam){
        String menuName = (String) requestParam.get("menuName");
        String status = (String) requestParam.get("status");
        //判断参数的有效性
        if(StringUtils.isEmpty(menuName) && StringUtils.isEmpty(status)){
            return listAdvMenuTree();
        }
        //生成带查询条件的AdvMenu,以待扩展使用
        AdvMenu advMenu = new AdvMenu();
        //进行格式有效性
        advMenu.setMenuName(StringUtils.trim(menuName));
        advMenu.setStatus(status);

        List<AdvMenuDto> menuDtoList = advMenuService.selectAdvMenuList(advMenu);
        return Result.success(menuDtoList);
    }

    @GetMapping("/getValidMenuTree")
    public Result selectMenuTreeStatusIsValid(){
        List<AdvMenuDto> advMenuDtoList = advMenuService.selectMenuTreeStatusIsValid();
        if(CollectionUtils.isEmpty(advMenuDtoList)){
            return Result.selectIsNullError();
        }

        return Result.success(advMenuDtoList);
    }

}
