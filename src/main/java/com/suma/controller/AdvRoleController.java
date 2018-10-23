package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.RoleException;
import com.suma.pojo.AdvRole;
import com.suma.service.iAdvRoleService;
import com.suma.utils.Result;
import com.suma.vo.AdvRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/17 0017
 * @Description 角色管理 controller
 **/
@RestController
@RequestMapping("/system/role")
public class AdvRoleController extends BaseController{

    @Autowired
    private iAdvRoleService advRoleService;

    /**
     * 新增角色
     * 因为涉及到多个表的操作所以必须加上事务的处理
     * @param advRoleVO
     * @return
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Result add(@Validated AdvRoleVO advRoleVO){
        AdvRole advRole = new AdvRole();
        BeanUtils.copyProperties(advRoleVO,advRole);

        return toResult(advRoleService.insertRole(advRole));
    }


    /**
     * 直接get请求默认请求全部信息
     *
     * @return
     */
    @GetMapping("/list")
    public Result getList(){
        return list(null,null,null,null,null,1,10);
    }


    /**
     * 分页查询角色信息
     *
     * @param roleName
     * @param roleKey
     * @param status
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/list")
    @Transactional(rollbackFor = Exception.class)
    public Result list(String roleName, String roleKey, String status,
                       String startTime, String endTime,
                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        PageInfo<AdvRole> advRolePageInfo = advRoleService.selectRoleList(roleName,roleKey,status,startTime,endTime);
        //如果查询数据为空,返回数据不存在
        if(CollectionUtils.isEmpty(advRolePageInfo.getList())){
            return Result.error("查询数据不存在");
        }
        return Result.success(advRolePageInfo);
    }

    @GetMapping("/selectValidRoles")
    public Result selectValidRoles(){
        return list(null,null,CommonConstants.NORMAL_STATUS
                        ,null,null,1,10);
    }

    /**
     * 删除角色
     * 涉及到多表操作，加事务进行处理
     * @param roleIds
     * @return
     */
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public Result remove(Integer roleIds){
        //对参数进行校验
        if(roleIds == null){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_ROLE_ID_IS_NULL);
        }

        return toResult(advRoleService.deleteRoleById(roleIds));
    }

    @PostMapping("/batchDelete")
    @Transactional(rollbackFor = Exception.class)
    public Result batchDelete(String roleIds){
        if(roleIds == null || roleIds.length() == 0){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_ROLE_ID_IS_NULL);
        }
        /**
         * 为了前端方便传递
         */
        String[] realRoleIds = roleIds.split(",");
        List<Integer> roleIdList = Lists.newArrayList();
        for(int i=0;i<realRoleIds.length;i++){
            roleIdList.add(Integer.valueOf(realRoleIds[i]));
        }

        return toResult(advRoleService.deleteRoleByIds(roleIdList));
    }


    /**
     * 更新角色表
     *
     * @param advRoleVO
     * @param roleId
     * @return
     */
    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@Validated AdvRoleVO advRoleVO,Integer roleId){
        //对roleId进行验证合法性
        if(roleId == null){
            throw new RoleException(ExceptionConstants.ROLE_EXCEPTION_ROLE_ID_IS_NULL);
        }

        AdvRole advRole = new AdvRole();
        advRole.setRoleId(roleId);
        BeanUtils.copyProperties(advRoleVO,advRole);

        return toResult(advRoleService.updateRole(advRole));

    }

}
