package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.InfoMaterialVO;
import com.suma.vo.NetAdvVO;
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
 * @description
 */
@RequestMapping("implant")
@RestController
public class ImplantAdvController extends BaseController {

    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private InfoRegionService infoRegionService;
    @Autowired
    private InfoMaterialService infoMaterialService;
    @Autowired
    private MovieMaterialService movieMaterialService;


    @RequestMapping("delete")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteNetAdv(String str) {
        List<Long> advInfoIds = StringUtil.convertstr(str);
        advInfoService.deleteAdvRelationInfo(advInfoIds);
        return Result.success();
    }


    /**
     * 更新植入广告
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("update")
    public Result updateNetAdv(String data) {
        NetAdvVO netAdvVO = JSON.parseObject(data, NetAdvVO.class);
        if (netAdvVO == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (netAdvVO.getId() == null || netAdvVO.getName() == null || netAdvVO.getStartDate() == null ||
                netAdvVO.getEndDate() == null || netAdvVO.getStatus() == null
                || netAdvVO.getMaterialType() == null || netAdvVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = netAdvVO.getAdvTypeId().equals(AdvContants.BEFORE_MOVIE_ADV_SUBTYPE_ID) || netAdvVO.getAdvTypeId().equals(AdvContants.SUSPAND_MOVIE_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("不是植入广告");
        }
        //更新广告信息
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(netAdvVO, advInfo);
        advInfo = UserAndTimeUtils.setEditUserAndTime(advInfo);
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新有效区域
        //删除之前的对应关系
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        //添加对应关系
        if (CollectionUtils.isEmpty(netAdvVO.getRegionId())) {
            throw new AdvInfoException("区域信息未填写");
        }
        infoRegionService.saveInfoRegion(netAdvVO.getRegionId(),advInfo.getId());
        //更新对应关系列表
        //删除之前的对应关系列表
        infoMaterialService.deleteByAdvInfoId(advInfo.getId());
        //添加对应关系列表
        if (CollectionUtils.isEmpty(netAdvVO.getInfoMaterialVOS())) {
            throw new AdvInfoException("未添加广告资源对应信息");
        }
        infoMaterialService.saveInfoMaterial(netAdvVO.getInfoMaterialVOS(),advInfo.getId());
        //获取视频源Id todo
        List<Long> list = netAdvVO.getVideoId();
        System.out.println(list);
        return Result.success();
    }

    /**
     * 获取视频源接口（模拟）todo
     *
     * @return
     */
    @RequestMapping(value = "getVedioMaterial")
    public Result getVedioMaterial() {
       /* List<MovieMaterial> movieMaterials = movieMaterialService.findALL();
        return Result.success(movieMaterials);*/
        List<AdvMaterial> list = advInfoService.getVedioMaterial();
        return Result.success(list);
    }


    /**
     * 植入广告添加接口
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("insert")
    public Result saveNetAdv(String data) {
        NetAdvVO netAdvVO = JSON.parseObject(data, NetAdvVO.class);
        if (netAdvVO == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        System.out.println(netAdvVO.getRegionId());
        if (netAdvVO.getName() == null || netAdvVO.getStartDate() == null ||
                netAdvVO.getEndDate() == null || netAdvVO.getStatus() == null
                || netAdvVO.getMaterialType() == null || netAdvVO.getAdvTypeId() == null
                || netAdvVO.getVideoId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = netAdvVO.getAdvTypeId().equals(AdvContants.BEFORE_MOVIE_ADV_SUBTYPE_ID)
                || netAdvVO.getAdvTypeId().equals(AdvContants.SUSPAND_MOVIE_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加植入广告");
        }
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(netAdvVO, advInfo);
        advInfo = UserAndTimeUtils.setCreateUserAndTime(advInfo);
        //如果是图片类型，则添加广告位
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)) {
            AdvLocation advLocation = netAdvVO.getAdvLocation();
            if (advLocation == null) {
                throw new AdvInfoException("未填写广告位信息");
            }
            //保存位置信息
            if (advLocation != null) {
                advLocationService.save(advLocation);
                //保存广告信息
                advInfo.setAdvLocationId(advLocation.getId());
            }
        }
        List<InfoMaterialVO> infoMaterialVOS = netAdvVO.getInfoMaterialVOS();
        //如果是片头广告，支持视频和图片，待设置
       /* if (netAdvVO.getAdvTypeId().equals(AdvContants.BEFORE_MOVIE_ADV_SUBTYPE_ID)) {
            if (advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
                if (infoMaterialVOS.size() > 1) {
                    throw new AdvMaterialException("片头视频仅支持一个视频");
                }
            }
            if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)) {
                if (infoMaterialVOS.size() > 5) {
                    throw new AdvMaterialException("片头广告图片不能多于5张");
                }
            }
        }*/

        //如果是暂停广告，仅支持图片
        if (netAdvVO.getAdvTypeId().equals(AdvContants.SUSPAND_MOVIE_ADV_SUBTYPE_ID)) {
            if (advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
                throw new AdvMaterialException("暂停广告仅支持图片");
            }
        }
        //保存广告信息
        advInfoService.save(advInfo);
        //保存区域
        if (CollectionUtils.isEmpty(netAdvVO.getRegionId())) {
            throw new AdvInfoException("未填写区域信息");
        }
        infoRegionService.saveInfoRegion(netAdvVO.getRegionId(),advInfo.getId());
        //保存资源关系列表
        if (CollectionUtils.isEmpty(infoMaterialVOS)) {
            throw new AdvInfoException("未填写资源信息列表");
        }
        infoMaterialService.saveInfoMaterial(infoMaterialVOS,advInfo.getId());
        //获取视频源Id todo
        List<Long> list = netAdvVO.getVideoId();
        System.out.println(list);
        return Result.success();
    }

    @RequestMapping("queryById")
    public Result selectById(Long id) {
        //创建前端显示对象
        NetAdvVO netAdvVO = new NetAdvVO();
        //查询出广告对应的信息
        AdvInfo advInfo = advInfoService.findByPK(id);
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        BeanUtils.copyProperties(advInfo, netAdvVO);
        //配置资源信息
        List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
        List<InfoMaterialVO> infoMaterialVOS = infoMaterialService.getInfoMaterialVOS(infoMaterials);
        netAdvVO.setInfoMaterialVOS(infoMaterialVOS);
        //配置广告位
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)) {
            netAdvVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        }
        //配置有效区域ID
        netAdvVO.setRegionId(infoRegionService.getRegionIds(advInfo.getId()));
        //获取视频源Id todo
        List<Long> list = Lists.newArrayList();
        list.add(622L);
        netAdvVO.setVideoId(list);
        return Result.success(netAdvVO);
    }
}
