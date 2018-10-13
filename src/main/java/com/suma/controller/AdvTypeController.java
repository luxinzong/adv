package com.suma.controller;

import com.suma.pojo.AdvType;
import com.suma.service.AdvTypeService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "insertAdvType",method = RequestMethod.POST)
    public Result insertAdvType(AdvType advType) {
        return result;
    }

    public Result deleteAdvTypeById(Long id) {
        advTypeService.deleteByAdvTypeId(id);
        return result;
    }
}

