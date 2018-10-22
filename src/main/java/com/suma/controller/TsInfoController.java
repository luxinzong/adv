package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.exception.BaseException;
import com.suma.pojo.TsInfo;
import com.suma.pojo.TsInfoExample;
import com.suma.service.TsService;
import com.suma.utils.Insert;
import com.suma.utils.Result;
import com.suma.utils.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@RestController
@RequestMapping("ts")
public class TsInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TsInfoController.class);

    @Autowired
    private TsService tsService;

    @RequestMapping("query")
    public Result queryTs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TsInfo> tsInfoList = tsService.findALL();
        return Result.success(new PageInfo<TsInfo>(tsInfoList));
    }

    @RequestMapping("add")
    public Result addTs(@Validated(Insert.class) TsInfo tsInfo) {
        tsService.checkExist(tsInfo);
        return Result.success(tsService.save(tsInfo));
    }


    @RequestMapping("update")
    public Result updateTs(@Validated(Update.class) TsInfo tsInfo) {
        tsService.checkExist(tsInfo);
        return Result.success(tsService.update(tsInfo));
    }

    @RequestMapping("delete")
    public Result deleteTs(Long id) {
        return Result.success(tsService.deleteByPK(id));
    }
}
