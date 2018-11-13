package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.AdvDept;
import com.suma.pojo.AdvInfo;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.AdvInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: luxinzong
 * @date: 2018/11/13 0013
 * @description 字幕广告 增删改查 接口
 */
@RestController
@RequestMapping("flywordAdv")
public class FlyWordAdvController extends BaseController {

    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private InfoFlywordService infoFlywordService;
    @Autowired
    private AdvInfoServiceGroupService advInfoServiceGroupService;
    @Autowired
    private InfoRegionService infoRegionService;
    @Autowired
    private AdvLocationService advLocationService;
    /**
     * 创建字幕广告接口
     * @param data
     * @return
     */
    @RequestMapping("insert")
    @Transactional(rollbackFor = Exception.class)
    public Result insert(String data) {
        //将data转换成JSON对象
        AdvInfoVO advInfoVO = JSON.parseObject(data, AdvInfoVO.class);
        //将广告信息保存到advInfo中
        if (advInfoVO.getName() == null || advInfoVO.getStartDate() == null ||
                advInfoVO.getEndDate() == null || advInfoVO.getStatus() == null
                || advInfoVO.getMaterialType() == null || advInfoVO.getAdvTypeId() == null){
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        AdvInfo advInfo = UserAndTimeUtils.setCreateUserAndTime();
        BeanUtils.copyProperties(advInfoVO, advInfo);

        return Result.success();
    }

    /**
     * 删除字幕广告接口
     * @param advInfoId
     * @return
     */
    @RequestMapping("delete")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(Long advInfoId) {
        return Result.success();
    }

    /**
     * 更新字幕广告接口
     * @param data
     * @return
     */
    @RequestMapping("update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(String data) {
        return Result.success();
    }

    /**
     * 根据ID查询字幕广告的所有信息
     * @param advInfoId
     * @return
     */
    @RequestMapping("select")
    public Result select(Long advInfoId) {
        //创建前端显示对象
        AdvInfoVO advInfoVO = new AdvInfoVO();
        //查询出ID为advInfoId的广告信息
        AdvInfo advInfo = advInfoService.findByPK(advInfoId);
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //设置字幕信息
        advInfoVO.setAdvFlyWords(infoFlywordService.getAdvFlyWords(advInfoId));
        BeanUtils.copyProperties(advInfo, advInfoVO);
        //设置频道信息
        advInfoServiceGroupService.getServiceGroup(advInfoVO);
        //设置有效区域ID
        advInfoVO.setRegionIds(infoRegionService.getRegionIds(advInfoId));
        //设置对应的广告位
        advInfoVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        return Result.success(advInfoVO);
    }


    /**
     * 按条件查询所有字幕广告
     * @param status
     * @param name
     * @param startDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @param advTypeId
     * @return
     */
    @RequestMapping("selectAll")
    public Result selectAll(Integer status, String name, String startDate, String endDate,
                            Integer pageNum, Integer pageSize, String advTypeId) {
        List<AdvInfo> list = advInfoService.selectAdvInfoByNameAndStatusAndOthor(status, name, startDate, endDate, pageNum, pageSize, advTypeId);
        PageInfo<AdvInfo> advInfoPageInfo = new PageInfo<>();
        advInfoPageInfo.setList(list);
        advInfoPageInfo.setTotal(list.size());
        return Result.success(advInfoPageInfo);
    }


}
