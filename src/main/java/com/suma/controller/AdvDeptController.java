package com.suma.controller;

import com.suma.constants.ExceptionConstants;
import com.suma.dto.AdvDeptDto;
import com.suma.exception.DeptException;
import com.suma.pojo.AdvDept;
import com.suma.service.iAdvDeptService;
import com.suma.utils.Result;
import com.suma.vo.AdvDeptVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 部门信息controller
 **/
@RestController
@RequestMapping(value = "/system/dept")
public class AdvDeptController extends BaseController{

    @Autowired
    private iAdvDeptService advDeptService;

    /**
     * 新增部门
     *
     * @param advDeptVO
     * @return
     */
    @RequestMapping("/add")
    public Result addAdvDept(@RequestBody @Validated AdvDeptVO advDeptVO){
        AdvDept advDept = new AdvDept();
        BeanUtils.copyProperties(advDeptVO,advDept);

        return toResult(advDeptService.insertAdvDept(advDept));
    }

    /**
     * 删除部门信息
     *
     * @return
     */
    @PostMapping("/delete")
    public Result removeAdvDept(@RequestBody Map<String,Object> requestMap){
        Integer deptId = (Integer) requestMap.get("deptId");
        if(deptId == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_ISNULL);
        }
       if(advDeptService.selectAdvDeptCount(deptId) > 0){
           return Result.error(ExceptionConstants.DEPT_EXCEPTION_EXIST_NEXT_DEPT);
       }
       if(advDeptService.checkAdvDeptExistUser(deptId)){
           return Result.error(ExceptionConstants.DEPT_EXCEPTION_DEPT_EXIST_USER);
       }

       return toResult(advDeptService.deleteAdvDeptById(deptId));
    }

    /**
     * 修改部门信息
     *
     * @param advDept
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/update")
    public Result updateAdvDept(@RequestBody @Validated AdvDept advDept){
        return toResult(advDeptService.updateAdvDept(advDept));
    }

    /**
     * 加载部门列表树
     *
     * @return list
     */
    @GetMapping("/list")
    public Result treeData(){
        List<AdvDeptDto> tree = advDeptService.selectAdvDeptTree();
        return Result.success(tree);
    }

    /**
     * 获取全部部门
     *
     * @return
     */
    @GetMapping("/listAll")
    public Result listAll(){
        List<AdvDeptDto> advDeptDtoList = advDeptService.selectAdvDeptAll();
        //应前台要求在返回list中，增加无以便使用
        AdvDeptDto advDeptDto = new AdvDeptDto();
        advDeptDto.setDeptId(0);
        advDeptDto.setDeptName("无");
        advDeptDto.setParentName("无");
        advDeptDtoList.add(0,advDeptDto);
        return Result.success(advDeptDtoList);
    }

    /**
     * 应前端要求，添加部门总数
     *
     * @return
     */
    @GetMapping("/deptCount")
    public Result getAdvDeptCount(){
        int count = advDeptService.getAdvDeptCount();
        return Result.success(count);
    }


    /**
     * 根据条件查询部门
     *
     * @param requestMap
     * @return
     */
    @PostMapping("/query")
    public Result query(@RequestBody Map<String,Object> requestMap){
        String deptName = (String) requestMap.get("deptName");
        String status = (String) requestMap.get("status");
        //无有效参数，返回treeData
        if(StringUtils.isBlank(deptName) && StringUtils.isBlank(status)){
            return treeData();
        }
        //使用advDept作为参数的目的是为了，方便未来扩展
        AdvDept advDept = new AdvDept();
        advDept.setDeptName(StringUtils.trim(deptName));
        advDept.setStatus(status);

        List<AdvDept> advDeptList = advDeptService.selectAdvDeptList(advDept);
        return Result.success(advDeptList);
    }

    /**
     * 查询有效状态树
     *
     * @return
     */
    @GetMapping("/selectValidDept")
    public Result selectValidDept(){
        List<AdvDeptDto> advDeptDtoList = advDeptService.selectDeptTreeStatusIsValid();
        if(CollectionUtils.isEmpty(advDeptDtoList)){
            return Result.selectIsNullError();
        }

        return Result.success(advDeptDtoList);
    }



}
