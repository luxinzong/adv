package com.suma.controller;

import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvLocationExample;
import com.suma.service.AdvLocationService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/12
 * @description:
 */
@RestController
@RequestMapping("location")
public class AdvLocationController {

    @Autowired
    private AdvLocationService advLocationService;

    @RequestMapping("queryLocation")
    public Result queryLocation(Long advTypeId) {
        Result result = new Result();
        AdvLocationExample example = new AdvLocationExample();
        if (advTypeId != null) {
            example.createCriteria().andAdvTypeIdEqualTo(advTypeId);
        }
        List<AdvLocation> advLocations = advLocationService.selectByExample(example);
        result.setResultCode(0);
        result.setResultData(advLocations);
        return result;
    }
}
