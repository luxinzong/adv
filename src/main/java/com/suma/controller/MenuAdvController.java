package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoRegion;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvLocationService;
import com.suma.service.InfoMaterialService;
import com.suma.service.InfoRegionService;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.InfoMaterialVO;
import com.suma.vo.MenuAdvVO;
import com.suma.vo.NetAdvVO;
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
@RestController
@RequestMapping("menu")
public class MenuAdvController extends BaseController {


    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private InfoMaterialService infoMaterialService;
    @Autowired
    private InfoRegionService infoRegionService;
    /**
     * 保存菜单广告
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("menuAdv")

    public Result saveMenuAdv(String data) {
        MenuAdvVO menuAdvVO = JSON.parseObject(data, MenuAdvVO.class);
        if (menuAdvVO == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (menuAdvVO.getName() == null || menuAdvVO.getStartDate() == null ||
                menuAdvVO.getEndDate() == null || menuAdvVO.getStatus() == null
                || menuAdvVO.getMaterialType() == null || menuAdvVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        try {
            //保存位置信息
            AdvLocation advLocation = menuAdvVO.getAdvLocation();
            if (advLocation == null) {
                throw new AdvInfoException("未填写广告位信息");
            }
            advLocationService.save(advLocation);
            //保存广告信息
            AdvInfo advInfo = UserAndTimeUtils.setCreateUserAndTime();
            BeanUtils.copyProperties(menuAdvVO, advInfo);
            advInfo.setAdvLocationId(advLocation.getId());
            advInfoService.save(advInfo);
            //保存区域信息
            if (CollectionUtils.isEmpty(menuAdvVO.getRegionIds())) {
                throw new AdvInfoException("未填写区域信息");
            }
            infoRegionService.saveInfoRegion(menuAdvVO.getRegionIds(), advInfo.getId());
            //保存关系列表
            if (CollectionUtils.isEmpty(menuAdvVO.getInfoMaterialVOS())) {
                throw new AdvInfoException("未添加资源列表");
            }
            infoMaterialService.saveInfoMaterial(menuAdvVO.getInfoMaterialVOS(), advInfo.getId());
        } catch (Exception e) {

        }
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("updateMenuAdv")
    public Result updateMenuAdv(String data) {
        MenuAdvVO menuAdvVO = JSON.parseObject(data, MenuAdvVO.class);
        if (menuAdvVO == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (menuAdvVO.getId() == null || menuAdvVO.getName() == null || menuAdvVO.getStartDate() == null ||
                menuAdvVO.getEndDate() == null || menuAdvVO.getStatus() == null
                || menuAdvVO.getMaterialType() == null || menuAdvVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        //更新广告信息
        AdvInfo advInfo = UserAndTimeUtils.setEditUserAndTime();
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新广告位
        AdvLocation advLocation = menuAdvVO.getAdvLocation();
        if (advLocation == null) {
            throw new AdvInfoException("未填写广告位信息");
        }
        advLocationService.update(advLocation);
        //更新有效区域
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        if (CollectionUtils.isEmpty(menuAdvVO.getRegionIds())) {
            throw new AdvInfoException("未填写区域信息");
        }
        infoRegionService.saveInfoRegion(menuAdvVO.getRegionIds(), advInfo.getId());
        //更新对应关系列表
        if (CollectionUtils.isEmpty(menuAdvVO.getInfoMaterialVOS())) {
            throw new AdvInfoException("未添加资源信息列表");
        }
        infoMaterialService.deleteByAdvInfoId(advInfo.getId());
        infoMaterialService.saveInfoMaterial(menuAdvVO.getInfoMaterialVOS(), advInfo.getId());
        return Result.success();
    }

    @RequestMapping("deleteMenuAdv")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteMenuAdv(String str) {
        List<Long> advInfoIds = StringUtil.convertstr(str);
        advInfoService.deleteAdvRelationInfo(advInfoIds);
        return Result.success();
    }

    @RequestMapping("queryById")
    public Result selectById(Long id) {
        //创建前端显示对象
        MenuAdvVO menuAdvVO = new MenuAdvVO();
        //查询出广告对应的信息
        AdvInfo advInfo = advInfoService.findByPK(id);
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        BeanUtils.copyProperties(advInfo, menuAdvVO);
        //配置资源信息
        List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
        List<InfoMaterialVO> infoMaterialVOS = infoMaterialService.getInfoMaterialVOS(infoMaterials);
        menuAdvVO.setInfoMaterialVOS(infoMaterialVOS);
        //配置广告位
        if (advInfo.getMaterialType().equals(2)) {
            menuAdvVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        }
        //配置有效区域ID
        menuAdvVO.setRegionIds(infoRegionService.getRegionIds(advInfo.getId()));
        return Result.success();
    }


}
