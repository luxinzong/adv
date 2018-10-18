package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvLocationExample;
import com.suma.service.AdvLocationService;
import com.suma.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/12
 * @description: 广告位置增删改查
 */
@RestController
@RequestMapping("location")
public class AdvLocationController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AdvLocationController.class);

    @Autowired
    private AdvLocationService advLocationService;

    /**
     * 获取location总数，方便分页显示
     *
     * @return
     */
    @RequestMapping("getLocationNum")
    public Result getLocationNum() {
        Result result = new Result();
        return Result.success(advLocationService.countByExample(null));
    }


    /**
     * @param advTypeId 广告位ID
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return
     */
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
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdvLocation> advLocations = advLocationService.selectByExample(example);

        return Result.success(advLocations);
    }

    @RequestMapping("addLocation")
    public Result addLocation(AdvLocation location) {
        Result result = new Result();
        if (location.getAdvTypeId() == null || location.getName() == null ||
                location.getxPosition() == null || location.getyPosition() == null ||
                location.getmHeight() == null || location.getmWidth() == null) {
            logger.info(JSON.toJSON(location).toString());
            result.setResultCode(1);
            result.setResultDesc("缺少参数");
            return result;
        }
        return toResult(advLocationService.save(location));
    }

    @RequestMapping("editLocation")
    public Result editLocation(AdvLocation location) {
        Result result = new Result();
        if (location.getId() == null || location.getAdvTypeId() == null || location.getName() == null ||
                location.getxPosition() == null || location.getyPosition() == null ||
                location.getmHeight() == null || location.getmWidth() == null) {
            result.setResultCode(1);
            result.setResultDesc("缺少参数");
            return result;
        }
        return toResult(advLocationService.update(location));
    }


    @RequestMapping("deleteLocations")
    public Result deleLocations(String ids) {
        Result result = new Result();
        if (ids == null) {
            result.setResultCode(1);
            result.setResultDesc("缺少参数");
            return result;
        }
        String[] idsSplit = ids.split(",");
        for (String id : idsSplit) {
            advLocationService.deleteByPK(Long.valueOf(id));
        }
        result.setResultCode(0);
        return result;
    }

}
