package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvCheckException;
import com.suma.exception.AdvFlyWordException;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.AdvCheckService;
import com.suma.service.AdvFlywordService;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvLocationService;
import com.suma.utils.Result;
import com.suma.vo.AdvCheckDetailVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luxinzong
 * @date 2018/10/20
 * @description 广告审核接口
 */
@RestController
@RequestMapping(value = "check",produces = "application/json;charset=utf-8")
public class AdvCheckDetailController extends BaseController{

    @Autowired
    AdvCheckService advCheckService;

    @Autowired
    AdvInfoService advInfoService;

    @Autowired
    AdvFlywordService advFlywordService;

    @Autowired
    AdvLocationService advLocationService;

    /**
     * 保存审核信息
     * @param advCheckDetail
     * @return 返回添加结果 Result
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result save(AdvCheckDetail advCheckDetail) {
        //判断参数是否为空，审核信息是否存在
        if (advCheckDetail == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        if (advCheckDetail.getAdvInfoId() != null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_DETAIL_IS_EXIST);
        }
        return toResult(advCheckService.insert(advCheckDetail));
    }

    /**
     * 删除单个信息
     * @param id
     * @return 返回删除结果 Result
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        if (id == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        if (advCheckService.select(id) == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_DETAIL_IS_NOT_EXIST);
        }
        return toResult(advCheckService.deleteById(id));
    }


    /**
     * 批量删除审核信息
     * @param str
     * @return 返回更新结果 Result
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result delete(String str) {
        if (StringUtils.isBlank(str)) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        String[] ids = str.substring(0, str.lastIndexOf(",")).split(",");
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (String id : ids) {
            if (advCheckService.select(Long.valueOf(id)) == null) {
                stringBuilder.append(id + ",");
                count ++;
                continue;
            }
            advCheckService.deleteById(Long.valueOf(id));
        }
        if (count == ids.length) {
            return toResult(ids.length);
        } else {
            return Result.error("审核信息ID为："+stringBuilder.toString()+"删除失败");
        }
    }

    /**
     * 更新审核信息
     * @param advCheckDetail
     * @return 返回更新结果 Result
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(AdvCheckDetail advCheckDetail) {
        System.out.println(advCheckDetail);
        if (advCheckDetail.getId() == null || advCheckDetail.getAdvInfoId() == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        if (advCheckService.select(advCheckDetail.getAdvInfoId()) == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_DETAIL_IS_NOT_EXIST);
        }
        return toResult(advCheckService.updateById(advCheckDetail));
    }

    /**
     * 查询所有审核信息
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public Result selectAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Result.success(advCheckService.selectAll());
    }

    /**
     * 查询单个广告审核信息详情
     * @param advInfoId
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result select(Long advInfoId) {
        //获取广告信息
        AdvInfo advInfo = advInfoService.findById(advInfoId);
        //判断广告信息是否存在
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        System.out.println(advInfo);
        //判断类型ID是否存在
        if (advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_ADV_TYPE_ID__NULL);
        }
        //获取广告位置信息
        AdvLocationExample example = new AdvLocationExample();
        example.createCriteria().andAdvTypeIdEqualTo(advInfo.getAdvTypeId());
        AdvLocation advLocation = advLocationService.selectByExample(example).get(0);

        //获取审核信息
        AdvCheckDetail advCheckDetail = advCheckService.select(advInfoId);

        //获取字幕广告信息
        AdvFlyWordExample example1 = new AdvFlyWordExample();
        example1.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        if (advFlywordService.selectByExample(example1).size() == 0) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWORD_IS_NOT_EXIST);
        }
        AdvFlyWord advFlyWord = advFlywordService.selectByExample(example1).get(0);

        //创建AdvCheckDetailVO页面对象，并给对应属性赋值
        AdvCheckDetailVO advCheckDetailVO = new AdvCheckDetailVO();
        BeanUtils.copyProperties(advInfo, advCheckDetailVO);
        BeanUtils.copyProperties(advCheckDetail, advCheckDetailVO);
        BeanUtils.copyProperties(advLocation, advCheckDetailVO);
        BeanUtils.copyProperties(advFlyWord, advCheckDetailVO);
        return Result.success(advCheckDetailVO);
    }



}
