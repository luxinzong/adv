package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.service.impl.AdvRegionService;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.BootAdvVO;
import com.suma.vo.InfoMaterialVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @auther: luxinzong
 * @date: 2018/11/12 0012
 * @description 开机广告 增删改查接口
 */
@RestController
@RequestMapping("boot")
public class BootAdvController extends BaseController {
    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private InfoRegionService infoRegionService;
    @Autowired
    private InfoMaterialService infoMaterialService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private InfoVersionService infoVersionService;
    @Autowired
    private AdvRegionService advRegionService;
    /**
     *  开机广告添加接口
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("insert")
    public Result insert(String data) {
        BootAdvVO bootAdvVO = JSON.parseObject(data, BootAdvVO.class);
        if (Objects.isNull(bootAdvVO)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = bootAdvVO.getAdvTypeId().equals(AdvContants.START_LOGO_ADV_LOCATION_ID)
                || bootAdvVO.getAdvTypeId().equals(AdvContants.START_MACHINE_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加开机广告");
        }
        try {
            //读取广告信息
            AdvInfo advInfo = new AdvInfo();
            BeanUtils.copyProperties(bootAdvVO, advInfo);
            //判断广告信息是否存在
            advInfoService.judgeAdvInfo(advInfo);
            advInfo = UserAndTimeUtils.setCreateUserAndTime(advInfo);
            //设置开机广告位
            advInfoService.setBootLocation(advInfo);
            advInfoService.save(advInfo);
            //获取广告信息ID
            Long advInfoId = advInfo.getId();
            //保存广告版本号
            //获取并保存区域信息
            List<Integer> regionIds = bootAdvVO.getRegionId();
            infoRegionService.saveInfoRegion(regionIds, advInfoId);
            //获取广告资源信息
            List<InfoMaterialVO> infoMaterialVOS = bootAdvVO.getInfoMaterialVOS();
            if (bootAdvVO.getMaterialType().equals(AdvContants.VEDIO_MATERIAL) & !CollectionUtils.isEmpty(infoMaterialVOS)) {
                if (infoMaterialVOS.size() > 1) {
                    throw new AdvMaterialException("开机视频广告仅支持单个视频");
                }
            }
            if (bootAdvVO.getMaterialType().equals(AdvContants.IMAGE_MATERIAL) & !CollectionUtils.isEmpty(infoMaterialVOS)) {
                if (infoMaterialVOS.size() > 5) {
                    throw new AdvMaterialException("开机图片广告最多可配置5张图片");
                }
            }
            infoMaterialService.saveInfoMaterial(infoMaterialVOS, advInfoId);
            //保存软件版本号
            infoVersionService.saveVersion(advInfoId,bootAdvVO.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Result.success();
    }

    /**
     * 广告信息编辑更新
     *
     * @return
     */
    @RequestMapping("update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(String data) {
        BootAdvVO bootAdvVO = JSON.parseObject(data, BootAdvVO.class);
        if (Objects.isNull(bootAdvVO)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = bootAdvVO.getAdvTypeId().equals(AdvContants.START_LOGO_ADV_LOCATION_ID)
                || bootAdvVO.getAdvTypeId().equals(AdvContants.START_MACHINE_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("不是开机广告类型");
        }
        try {
            //获取广告信息
            AdvInfo advInfo = new AdvInfo();
            BeanUtils.copyProperties(bootAdvVO, advInfo);
            advInfo = UserAndTimeUtils.setEditUserAndTime(advInfo);
            advInfoService.updateByPrimaryKeySelective(advInfo);
            Long advInfoId = advInfo.getId();
            //删除原来的区域信息
            infoRegionService.deleteByAdvInfoId(advInfoId);
            //获取区域信息,并保存
            List<Integer> regionIds = bootAdvVO.getRegionId();
            infoRegionService.saveInfoRegion(regionIds, advInfoId);
            //删除原来资源信息
            infoMaterialService.deleteByAdvInfoId(advInfoId);
            //获取资源对应信息
            infoMaterialService.saveInfoMaterial(bootAdvVO.getInfoMaterialVOS(), advInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Result.success();
    }

    /**
     * 删除广告信息
     * @param str 广告ID字符串
     * @return Result -> 删除成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete")
    public Result delete(String str) {
        //判断字符串参数是否为空
        if (StringUtils.isBlank(str)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        try {
            //将数组转换成list集合
            List<Long> advInfoIds = StringUtil.convertstr(str);
            if (!CollectionUtils.isEmpty(advInfoIds)) {
                advInfoService.deleteAdvRelationInfo(advInfoIds);
                infoVersionService.deleteByAdvInfoIds(advInfoIds,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return Result.success();
    }

    /**
     * 获取最新软件版本号
     *
     * @return
     */
    @RequestMapping("getVersion")
    public Result getUpToDateVersion(Integer regionId, Long advTypeId) {
        Integer version = infoVersionService.getUpToDateVersion(regionId, advTypeId);
        return Result.success(version);
    }


    @RequestMapping(value = "queryById")
    public Result query(Long id) {
        //创建一个给前端显示的VO对象
        BootAdvVO bootAdvVO = new BootAdvVO();
        //查询出对应ID的广告信息
        AdvInfo advInfo = advInfoService.findById(id);
        //判断广告信息是否存在
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
        //创建一个前端显示资源列表的VO对象
        List<InfoMaterialVO> infoMaterialVOS =infoMaterialService.getInfoMaterialVOS(infoMaterials);
        //将广告资源列表存储到前对显示的广告信息VO对象中
        bootAdvVO.setInfoMaterialVOS(infoMaterialVOS);
        //将广告信息存储到前端显示VO对象中
        BeanUtils.copyProperties(advInfo,bootAdvVO);
        //将区域名称和子名称查询出来
        //所有区域
        bootAdvVO.setAdvRegions(advRegionService.selectAdvRegionAll());
        //有效区域ID
        bootAdvVO.setRegionId(infoRegionService.getRegionIds(advInfo.getId()));
        return Result.success(bootAdvVO);
    }
}
