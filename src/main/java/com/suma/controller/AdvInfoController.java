package com.suma.controller;

import com.suma.pojo.AdvInfo;
import com.suma.service.AdvInfoService;
import com.suma.utils.Result;
import com.suma.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author luxinzong
 * @date 2018/10/13
 */

@RestController
@RequestMapping(value = "advInfo",produces = "application/json;charset=utf-8")
public class AdvInfoController {

    @Autowired
    AdvInfoService advInfoService;

    @Autowired
    Result result;

    @Autowired
    StringUtils stringUtils;

    /**
     * 创建广告
     * @param advInfo 广告信息
     * @return
     */
    @RequestMapping(value = "insert/advInfo", method = RequestMethod.POST)
    public Result insertAdvInfo(AdvInfo advInfo) {
        advInfoService.insertAdvInfo(advInfo);
        return result;
    }

    /**
     * 删除广告
     * @param ids 广告信息id
     * @return
     */
    @RequestMapping(value = "delete/advInfo", method = RequestMethod.POST)
    public Result deleteAdvInfo(List<Long> ids) {
        if (ids != null) {
            for (Long id : ids
            ) {
                advInfoService.deleteByAdvInfoId(id);
            }
        }
        return result;
    }

    /**
     * 更新广告信息
     * @param advInfo 广告信息
     * @return
     */
    @RequestMapping(value = "update/advInfo", method = RequestMethod.POST)
    public Result updateAdvInfo(AdvInfo advInfo) {
        advInfoService.updateAdvInfo(advInfo);
        return result;
    }

    /**
     * 查询所有广告信息
     * @return
     */
    @RequestMapping(value = "select/advInfos", method = RequestMethod.POST)
    public Result selectAdvInfos() {
        advInfoService.selectAdvInfos();
        return result;
    }

    /**
     * 按给定条件进行查询
     * @param name 广告名称
     * @param status 广告状态
     * @param startDate 广告起始日期
     * @param endDate 广告结束日期
     * @return
     */
    @RequestMapping(value = "query/advInfosByNameOrStatusOrDate", method = RequestMethod.POST)
    public Result selectAdvInfosByNameOrStatusOrDate(
            String name, Integer status, String startDate, String endDate) {
        Date start = stringUtils.StrToDate(startDate);
        Date end = stringUtils.StrToDate(endDate);
        advInfoService.selectByNameAndStatusAndDate(name, status, start, end);
        return result;
    }

}

