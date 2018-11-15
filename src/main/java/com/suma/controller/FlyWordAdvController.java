package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
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
import org.springframework.util.CollectionUtils;
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
    @Autowired
    private InfoMaterialService infoMaterialService;

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
        Boolean flag = advInfoVO.getAdvTypeId().equals(AdvContants.POP_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加弹出广告");
        }
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        //判断广告信息是否存在
        advInfoService.judgeAdvInfo(advInfo);
        advInfo = UserAndTimeUtils.setCreateUserAndTime(advInfo);
        //获取广告位信息
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation != null) {
            advLocationService.save(advLocation);
            advInfo.setAdvLocationId(advLocation.getId());
        } else {
            throw new AdvInfoException(AdvContants.LOCATION_IS_NULL);
        }
        //保存广告信息
        advInfoService.save(advInfo);
        //获取广告信息ID
        Long advInfoId = advInfo.getId();
        //获取字幕广告信息，保存字幕广告信息
        if (advInfo.getMaterialType().equals(AdvContants.TEXT_MATERIAL)) {
            List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
            if (CollectionUtils.isEmpty(advFlyWords)) {
                throw new AdvInfoException(AdvContants.FLYWORDS_IS_NULL);
            }
            infoFlywordService.saveFlyWords(advFlyWords, advInfoId);
        }
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)) {
           List<InfoMaterialVO> infoMaterialVOS =  advInfoVO.getInfoMaterialVOS();
            if (infoMaterialVOS.size() > 5) {
                throw new AdvMaterialException("仅支持配置最多5张图片");
            }
            infoMaterialService.saveInfoMaterial(infoMaterialVOS, advInfoId);
        }
        //保存频道信息
        advInfoServiceGroupService.saveServiceInfomation(advInfoVO, advInfoId);
        //保存区域信息
        if (CollectionUtils.isEmpty(advInfoVO.getRegionId())) {
            throw new AdvInfoException(AdvContants.REGION_ID_IS_NULL);
        }
        infoRegionService.saveInfoRegion(advInfoVO.getRegionId(), advInfoId);
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
        //删除频道信息
        advInfoServiceGroupService.deleteAdvServicenByAdvInfoId(advInfoIds);
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
        Boolean flag = advInfoVO.getAdvTypeId().equals(AdvContants.POP_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("不是弹出广告");
        }
        if (advInfoService.findByPK(advInfoVO.getId()) == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //获取广告位
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation != null) {
            advLocationService.updateByPrimaryKeySelective(advLocation);
        } else {
            throw new AdvInfoException(AdvContants.LOCATION_IS_NULL);
        }
        //设置编辑信息
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        advInfo = UserAndTimeUtils.setEditUserAndTime(advInfo);
        //保存广告信息
        advInfoService.updateByPrimaryKeySelective(advInfo);
        Integer materialType = advInfo.getMaterialType();
        //更新字幕信息
        //获取字幕信息,更新数据库
        if (materialType.equals(AdvContants.TEXT_MATERIAL)) {
            infoFlywordService.deletByAdvInfoId(advInfo.getId());
            List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
            if (CollectionUtils.isEmpty(advFlyWords)) {
                throw new AdvInfoException(AdvContants.FLYWORDS_IS_NULL);
            }
            infoFlywordService.saveFlyWords(advFlyWords, advInfo.getId());
        }
        if (materialType.equals(AdvContants.IMAGE_MATERIAL)) {
            //删除原来资源信息
            infoMaterialService.deleteByAdvInfoId(advInfo.getId());
            //获取资源对应信息
            infoMaterialService.saveInfoMaterial(advInfoVO.getInfoMaterialVOS(), advInfo.getId());
        }
        //更新区域信息
        if (CollectionUtils.isEmpty(advInfoVO.getRegionId())) {
            throw new AdvInfoException(AdvContants.REGION_ID_IS_NULL);
        }
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        infoRegionService.saveInfoRegion(advInfoVO.getRegionId(), advInfo.getId());
        /* 读取频道信息,更新数据库 */
        advInfoServiceGroupService.deleteAdvServiceByAdvInfoId(advInfo.getId());
        advInfoServiceGroupService.updateServiceGroup(advInfoVO);
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
        Integer materialType = advInfo.getMaterialType();
        if (materialType.equals(AdvContants.TEXT_MATERIAL)) {
            advInfoVO.setAdvFlyWords(infoFlywordService.getAdvFlyWords(id));
        }
        if (materialType.equals(AdvContants.IMAGE_MATERIAL)) {
            List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
            //创建一个前端显示资源列表的VO对象
            List<InfoMaterialVO> infoMaterialVOS =infoMaterialService.getInfoMaterialVOS(infoMaterials);
            advInfoVO.setInfoMaterialVOS(infoMaterialVOS);
        }
        BeanUtils.copyProperties(advInfo, advInfoVO);

        //设置有效区域ID
        advInfoVO.setRegionId(infoRegionService.getRegionIds(id));
        //设置直播或点播
        if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_LIVE)) {
            advInfoVO.setType(AdvContants.getTypeMap().get(AdvContants.SERVICE_GROUP_STATUS_LIVE));
        } else if (advInfoVO.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_VOD)) {
            advInfoVO.setType(AdvContants.getTypeMap().get(AdvContants.SERVICE_GROUP_STATUS_VOD));
        } else if (advInfo.getReservedInt().equals(AdvContants.SERVICE_GROUP_STATUS_ACTIVE)) {
            //设置频道信息
            advInfoServiceGroupService.getServiceGroup(advInfoVO);
        }
        //设置对应的广告位
        advInfoVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        return Result.success(advInfoVO);
    }
}
