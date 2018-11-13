package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.AdvInfoVO;
import com.suma.vo.InfoMaterialVO;
import org.apache.commons.lang3.StringUtils;
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
        //判断广告信息是否存在
        advInfoService.judgeAdvInfo(advInfo);
        //获取广告位信息
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation != null) {
            advLocationService.save(advLocation);
            advInfo.setAdvLocationId(advLocation.getId());
        }
        //保存广告信息
        advInfoService.save(advInfo);
        //获取广告信息ID
        Long advInfoId = advInfo.getId();
        //获取字幕广告信息，保存字幕广告信息
        List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
        infoFlywordService.saveFlyWords(advFlyWords, advInfoId);
        //保存频道信息
        advInfoServiceGroupService.saveServiceInfomation(advInfoVO, advInfoId);
        //保存区域信息
        infoRegionService.saveInfoRegion(advInfoVO.getRegionIds(), advInfoId);
        return Result.success();
    }

    /**
     * 删除字幕广告接口
     * @param str
     * @return
     */
    @RequestMapping("delete")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(String str) {
        if (StringUtils.isBlank(str)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        List<Long> advInfoIds = StringUtil.convertstr(str);
        //删除广告信息
        advInfoService.deleteByAdvInfoIds(advInfoIds);
        //删除广告对应字幕列表
        infoFlywordService.deleteByAdvInfoIds(advInfoIds);
        //删除广告信息对应频道信息
        advInfoServiceGroupService.deleteAdvServicenByAdvInfoId(advInfoIds);
        //删除广告对应区域信息
        infoRegionService.deleteByAdvInfoIds(advInfoIds);
        //删除广告位
        advInfoService.deleteAdvLocationByAdvInfoIds(advInfoIds);
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
        AdvInfoVO advInfoVO = JSON.parseObject(data, AdvInfoVO.class);
        //判断是否缺少参数
        if (advInfoVO.getId() == null || advInfoVO.getName() == null || advInfoVO.getStartDate() == null ||
                advInfoVO.getEndDate() == null || advInfoVO.getStatus() == null
                || advInfoVO.getMaterialType() == null || advInfoVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (advInfoService.findByPK(advInfoVO.getId()) == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //获取广告位
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation != null) {
            advLocationService.update(advLocation);
        }
        //设置编辑信息
        AdvInfo advInfo = UserAndTimeUtils.setEditUserAndTime();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        //保存广告信息
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新字幕信息
        //获取字幕信息,更新数据库
        infoFlywordService.deletByAdvInfoId(advInfo.getId());
        List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
        infoFlywordService.saveFlyWords(advFlyWords, advInfo.getId());
        //更新区域信息
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        infoRegionService.saveInfoRegion(advInfoVO.getRegionIds(), advInfo.getId());
        return Result.success();
    }

    /**
     * 根据ID查询字幕广告的所有信息
     * @param id
     * @return
     */
    @RequestMapping("queryById")
    public Result select(Long id) {
        //创建前端显示对象
        AdvInfoVO advInfoVO = new AdvInfoVO();
        //查询出ID为advInfoId的广告信息
        AdvInfo advInfo = advInfoService.findByPK(id);
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //设置字幕信息
        advInfoVO.setAdvFlyWords(infoFlywordService.getAdvFlyWords(id));
        BeanUtils.copyProperties(advInfo, advInfoVO);
        //设置频道信息
        advInfoServiceGroupService.getServiceGroup(advInfoVO);
        //设置有效区域ID
        advInfoVO.setRegionIds(infoRegionService.getRegionIds(id));
        //设置对应的广告位
        advInfoVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        return Result.success(advInfoVO);
    }
}
