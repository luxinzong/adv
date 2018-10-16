package com.suma.controller;

import com.suma.constants.ExceptionConstants;
import com.suma.dto.AdvDeptDto;
import com.suma.exception.DeptException;
import com.suma.pojo.AdvDept;
import com.suma.service.iAdvDeptService;
import com.suma.utils.Result;
import com.suma.vo.AdvDeptVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/add")
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
    public List<AdvDeptDto> treeData(){
        List<AdvDeptDto> tree = advDeptService.selectAdvDeptTree();
        return tree;
    }

    @GetMapping("/listAll")
    public List<AdvDept> listAll(){
        List<AdvDept> advDeptList = advDeptService.selectAdvDeptAll();
        return advDeptList;
    }

    @PostMapping("/query")
    public List<AdvDeptDto> query(@RequestBody AdvDept advDept){
        String deptName = advDept.getDeptName();
        String status = advDept.getStatus();
        //无有效参数，返回treeData
        if(StringUtils.isBlank(deptName) && status == null){
            return treeData();
        }
        return advDeptService.selectAdvDeptList(advDept);
    }


}
