package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;

import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@RestController
@RequestMapping(value = "info", produces = "application/json;charset=utf-8")
public class AdvInfoContoller{

    @Autowired
    private AdvInfoService advInfoService;

    /**
     * 查询广告信息
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Result queryAdvInfo(@RequestBody Map<String,Object> requestMap){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer status = (Integer) requestMap.get("status");
        String name = (String) requestMap.get("name");
        String start = (String) requestMap.get("startDate");
        String end = (String) requestMap.get("endDate");
        Integer pageNum = (Integer) requestMap.get("pageNum");
        Integer pageSize = (Integer) requestMap.get("pageSize");
                Result result = new Result();
        try {
            Date startDate = (Date) sdf.parse(start);
            Date endDate = (Date) sdf.parse(end);
            if (pageNum == null || pageSize == null) {
                result.setResultCode(1);
                result.setResultDesc("缺少页码");
                return result;
            }
            AdvInfoExample advInfoExample = new AdvInfoExample();
            advInfoExample.createCriteria().andStartDateEqualTo(startDate).
                    andStatusEqualTo(status).andEndDateEqualTo(endDate);
            PageHelper.startPage(pageNum, pageSize);
            List<AdvInfo> advInfoList = advInfoService.selectByExample(advInfoExample);
            result.setResultDesc("查询成功");
            result.setResultCode(0);
            result.setResultData(advInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 删除广告信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfo(@RequestBody List<Long> ids) {
        Result result = new Result();
        try {
            if (ids == null) {
                result.setResultData(null);
                result.setResultCode(1);
                result.setResultDesc("缺少必须参数");
            }
            for (Long id : ids) {
                //删除所有广告及所对应资源关系
                advInfoService.deleteByPK(id);
            }
            result.setResultData(null);
            result.setResultCode(0);
            result.setResultDesc("成功删除"+ids.size()+"个广告信息");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建广告信息
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insertAdvInfo(@RequestBody AdvInfo advInfo) {
        Result result = new Result();
        try {
            System.out.println(advInfo);
            if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                    advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                    || advInfo.getPeriodTimeStart() == null) {
                result.setResultDesc("缺少参数");
                result.setResultCode(1);
                return result;
            }
            String name = advInfo.getName();
            //检查该名称广告是否存在
            AdvInfoExample example = new AdvInfoExample();
            example.createCriteria().andNameEqualTo(name);
            List<AdvInfo> advInfoList = advInfoService.selectByExample(example);
            if (advInfoList.size() != 0) {
                result.setResultCode(1);
                result.setResultDesc("该广告已存在");
                result.setResultData(null);
                return result;
            }
            //若不存在,则创建广告信息以及对应广告资源
            advInfoService.save(advInfo);
            result.setResultCode(0);
            result.setResultData(advInfo);
            result.setResultDesc("创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 编辑广告信息
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAdvInfo(@RequestBody AdvInfo advInfo) {
        Result result = new Result();
        try {
            if (advInfo != null) {
                Long id = advInfo.getId();
                if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                        advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                        || advInfo.getPeriodTimeStart() == null) {
                    result.setResultCode(1);
                    result.setResultDesc("缺少参数");
                    result.setResultData(null);
                    return result;
                }
                advInfoService.update(advInfo);
                result.setResultData(advInfo);
                result.setResultDesc("更新成功");
                result.setResultCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}



















