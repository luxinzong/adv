package com.suma.controller;

import com.suma.pojo.AdvType;
import com.suma.service.AdvTypeService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luxinzong
 * @date 2018/10/13
 */

@RestController
@RequestMapping(value = "advType",produces = "application/json;charset=utf-8")
public class AdvTypeController {
    @Autowired
    AdvTypeService advTypeService;

    @Autowired
    Result result;

    /**
     *  创建广告位
     * @param advType 广告位
     * @return
     */
    @RequestMapping(value = "insert/AdvType",method = RequestMethod.POST)
    public Result insertAdvType(AdvType advType) {
        return result;
    }

    /**
     * 删除广告位
     * @param ids 要删除的广告id
     * @return
     */
    @RequestMapping(value = "delete/AdvType",method = RequestMethod.POST)
    public Result deleteAdvType(List<Long> ids) {
        for (Long id : ids
        ) {
            advTypeService.deleteByAdvTypeId(id);
        }
        return result;
    }

    /**
     *  编辑广告位信息
     * @param advType 广告位
     * @return
     */
    @RequestMapping(value = "update/AdvType", method = RequestMethod.POST)
    public Result updateAdvType(AdvType advType) {
        advTypeService.updateAdvType(advType);
        return result;
    }

    /**
     *  根据广告位类型查询广告位
     * @param advType 广告位类型
     * @return
     */
    @RequestMapping(value = "query/advTypesByType", method = RequestMethod.POST)
    public Result selectAdvTypesByType(String advType) {
        advTypeService.selectAdvTypesByType(advType);
        return result;
    }

    @RequestMapping(value = "query/advTypes", method = RequestMethod.POST)
    public Result selectAdvTypes() {
        advTypeService.selectAdvTypes();
        return result;
    }

}

