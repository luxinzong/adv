package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.suma.pojo.AdvCheckDetail;
import com.suma.pojo.AdvInfoExample;
import com.suma.service.AdvCheckService;
import com.suma.service.AdvInfoService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luxinzong
 * @date 2018/10/20
 * @description
 */
@RestController
@RequestMapping(value = "advCheck",produces = "application/json;charset=utf-8")
public class AdvCheckDetailController extends BaseController{

    @Autowired
    AdvCheckService advCheckService;

    @Autowired
    AdvInfoService advInfoService;

    /**
     * 保存审核信息
     * @param advCheckDetail
     * @return 返回添加结果 Result
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(AdvCheckDetail advCheckDetail) {
        return toResult(advCheckService.insert(advCheckDetail));
    }


    /**
     * 删除单个信息
     * @param id
     * @return 返回删除结果 Result
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        return toResult(advCheckService.deleteById(id));
    }


    /**
     * 批量删除审核信息
     * @param id
     * @return 返回更新结果 Result
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result delete(Long[] id) {
        return toResult(advCheckService.deleteAll(id));
    }


    /**
     * 更新审核信息
     * @param advCheckDetail
     * @return 返回更新结果 Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(AdvCheckDetail advCheckDetail) {
        return toResult(advCheckService.updateById(advCheckDetail));
    }


    /**
     * 查询所有审核信息
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public Result selectAll(Integer status,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Result.success(advCheckService.selectAll());
    }


    /**
     * 查询所有待审核信息
     * @param status
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "selectAdvChecks", method = RequestMethod.POST)
    public Result selectByStatus(Integer status,Integer pageNum,Integer pageSize) {
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andStatusEqualTo(status);
        PageHelper.startPage(pageNum, pageSize);
        return Result.success(advInfoService.selectByExample(example));
    }


    /**
     * 查询单个广告审核信息详情
     * @param advInfoId
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "select", method = RequestMethod.POST)
    public Result select(Long advInfoId) {
        advCheckService.select(advInfoId);
        advInfoService.findById(advInfoId);
        return Result.success(advInfoService.findById(advInfoId));
    }

}
