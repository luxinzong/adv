package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvLocationExample;
import com.suma.pojo.PageBean;
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
    public Result queryLocation(Long advTypeId, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        if (pageNum == null || pageSize == null) {
            result.setResultCode(1);
            result.setResultDesc("缺少参数");
            return result;
        }
        AdvLocationExample example = new AdvLocationExample();
        if (advTypeId != null) {
            example.createCriteria().andAdvTypeIdEqualTo(advTypeId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AdvLocation> advLocations = advLocationService.selectByExample(example);
        PageBean<AdvLocation> pageData = new PageBean<>(pageNum, pageSize, advLocations.size());
        pageData.setItems(advLocations);
        result.setResultCode(0);
        result.setResultData(pageData.getItems());
        return result;
    }

    @RequestMapping("addLocation")
    public Result addLocation(AdvLocation location) {
        Result result = new Result();
        if (location.getAdvTypeId() == null || location.getName() == null ||
                location.getxPosition() == null || location.getyPosition() == null ||
                location.getmHeight() == null || location.getmWidth() == null) {
            result.setResultCode(1);
            result.setResultDesc("缺少参数");
            return result;
        }
        int save = advLocationService.save(location);
        if (save == 0) {
            result.setResultCode(1);
            result.setResultDesc("添加失败");
            return result;
        }
        result.setResultCode(0);
        return result;
    }

}
