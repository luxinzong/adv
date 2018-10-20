package com.suma.controller;

import com.suma.pojo.AdvCheckDetail;
import com.suma.service.AdvCheckService;
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


    /**
     * 保存审核信息
     * @param advCheckDetail
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(AdvCheckDetail advCheckDetail) {
        return toResult(advCheckService.insert(advCheckDetail));
    }


    /**
     * 删除单个信息
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        return toResult(advCheckService.deleteById(id));
    }


    /**
     * 批量删除审核信息
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result delete(Long[] id) {
        return toResult(advCheckService.deleteAll(id));
    }


    /**
     * 更新审核信息
     * @param advCheckDetail
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(AdvCheckDetail advCheckDetail) {
        return toResult(advCheckService.updateById(advCheckDetail));
    }




    /**
     * 查询所有审核信息
     * @return
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public Result selectAll() {
        return Result.success(advCheckService.selectAll());
    }



    /**
     * 查询单个广告审核信息
     * @param id
     * @return
     */
    @RequestMapping(value = "select", method = RequestMethod.POST)
    public Result select(Long id) {
        return Result.success(advCheckService.select(id));
    }

}
