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
import com.suma.utils.StringUtil;
import com.suma.vo.AdvCheckDetailVO;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
     * @param advCheckDetail 审核信息
     * @return Result 添加成功或失败
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result save(AdvCheckDetail advCheckDetail) {
        //判断参数是否为空，审核信息是否存在
        if (advCheckDetail == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //判断该审核信息是否已存在，若存在，提示用户审核信息已存在
        if (advCheckDetail.getId() != null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_DETAIL_IS_EXIST);
        }
        return toResult(advCheckService.insert(advCheckDetail));
    }

    /**
     * 删除单个信息
     * @param id 审核信息ID
     * @return 返回删除结果 Result -> 删除成功或失败
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        if (id == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //根据id查询，检查是否存在审核信息
        if (advCheckService.select(id) == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_DETAIL_IS_NOT_EXIST);
        }
        return toResult(advCheckService.deleteById(id));
    }


    /**
     * 批量删除审核信息
     * @param str 审核信息ID字符串
     * @return 返回更新结果 Result 删除成功或失败
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result delete(String str) {
        //判断id字符串是否为空
        if (StringUtils.isBlank(str)) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //将id字符串转解析成字符串数组
        List<Long> idsList = StringUtil.convertstr(str);
        advCheckService.deleteAll((Long[]) idsList.toArray());
        return Result.success();
    }

    /**
     * 更新审核信息
     * @param advCheckDetail 审核信息
     * @return 返回更新结果 Result 更新成功或失败
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
     * @param pageNum 页码
     * @param pageSize 显示条数
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public Result selectAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Result.success(advCheckService.selectAll());
    }

    /**
     * 查询单个广告审核信息详情
     * @param advInfoId 广告ID
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result select(Long advInfoId) {
        //创建审核信息前端VO对象
        AdvCheckDetailVO advCheckDetailVO = new AdvCheckDetailVO();
        //获取广告信息
        AdvInfo advInfo = advInfoService.findById(advInfoId);
        //判断广告信息是否存在
        if (advInfo != null) {
            System.out.println(advInfo);
            //判断类型ID是否存在
            if (advInfo.getAdvTypeId() == null) {
                throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_ADV_TYPE_ID__NULL);
            }
            //获取广告位置信息
            AdvLocationExample example = new AdvLocationExample();
            example.createCriteria().andAdvTypeIdEqualTo(advInfo.getAdvTypeId());
            AdvLocation advLocation = new AdvLocation();
            if (advLocationService.selectByExample(example).size() != 0) {
                advLocation = advLocationService.selectByExample(example).get(0);
            } else {
                System.out.println("位置信息不存在");
            }
            //获取审核信息
            AdvCheckDetail advCheckDetail = advCheckService.select(advInfoId);
            if (advCheckDetail == null) {
                System.out.println("审核信息不存在");
            }
            //获取字幕广告信息
            AdvFlyWordExample example1 = new AdvFlyWordExample();
            example1.createCriteria().andAdvInfoIdEqualTo(advInfoId);
            AdvFlyWord advFlyWord = new AdvFlyWord();
            if (advFlywordService.selectByExample(example1).size() != 0) {
                advFlyWord = advFlywordService.selectByExample(example1).get(0);
            } else {
                System.out.println("字幕不存在");
            }
            //给AdvCheckDetailVO页面对象属性赋值
            BeanUtils.copyProperties(advInfo, advCheckDetailVO);
            BeanUtils.copyProperties(advCheckDetail, advCheckDetailVO);
            BeanUtils.copyProperties(advLocation, advCheckDetailVO);
            BeanUtils.copyProperties(advFlyWord, advCheckDetailVO);
        } else {
            System.out.println("广告不存在");
        }
        return Result.success(advCheckDetailVO);
    }
}
