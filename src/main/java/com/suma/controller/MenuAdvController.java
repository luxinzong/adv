package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
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
    @RequestMapping("insert")
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
        Boolean flag = menuAdvVO.getAdvTypeId().equals(AdvContants.MAIN_MENU_ADV_SUBTYPE_ID)
                || menuAdvVO.getAdvTypeId().equals(AdvContants.LIST_ADV_SUBTYPE_ID)
                || menuAdvVO.getAdvTypeId().equals(AdvContants.VOLUM_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加菜单或节目列表或音量条广告");
        }
        //保存位置信息
        AdvLocation advLocation = menuAdvVO.getAdvLocation();
        if (advLocation == null) {
            throw new AdvInfoException("未填写广告位信息");
        }
        advLocationService.save(advLocation);
        //保存广告信息
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(menuAdvVO, advInfo);
        //判断广告信息是否存在
        advInfoService.judgeAdvInfo(advInfo);
        advInfo = UserAndTimeUtils.setCreateUserAndTime(advInfo);
        advInfo.setAdvLocationId(advLocation.getId());
        advInfoService.save(advInfo);
        //保存区域信息
        if (CollectionUtils.isEmpty(menuAdvVO.getRegionId())) {
            throw new AdvInfoException("未填写区域信息");
        }
        infoRegionService.saveInfoRegion(menuAdvVO.getRegionId(), advInfo.getId());
        //保存关系列表
        if (CollectionUtils.isEmpty(menuAdvVO.getInfoMaterialVOS())) {
            throw new AdvInfoException("未添加资源列表");
        }
        if (advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
            throw new AdvMaterialException("菜单广告、节目列表、音量条广告仅支持图片");
        }
        infoMaterialService.saveInfoMaterial(menuAdvVO.getInfoMaterialVOS(), advInfo.getId());
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("update")
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
        Boolean flag = menuAdvVO.getAdvTypeId().equals(AdvContants.MAIN_MENU_ADV_SUBTYPE_ID)
                || menuAdvVO.getAdvTypeId().equals(AdvContants.LIST_ADV_SUBTYPE_ID)
                || menuAdvVO.getAdvTypeId().equals(AdvContants.VOLUM_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("不是菜单或节目列表或音量条广告");
        }
        //更新广告信息
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(menuAdvVO, advInfo);
        advInfo = UserAndTimeUtils.setEditUserAndTime(advInfo);
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新有效区域
        if (advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
            throw new AdvMaterialException("菜单广告、节目列表、音量条广告仅支持图片");
        }
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        if (CollectionUtils.isEmpty(menuAdvVO.getRegionId())) {
            throw new AdvInfoException("未填写区域信息");
        }
        infoRegionService.saveInfoRegion(menuAdvVO.getRegionId(), advInfo.getId());
        //更新对应关系列表
        if (CollectionUtils.isEmpty(menuAdvVO.getInfoMaterialVOS())) {
            throw new AdvInfoException("未添加资源信息列表");
        }
        infoMaterialService.deleteByAdvInfoId(advInfo.getId());
        infoMaterialService.saveInfoMaterial(menuAdvVO.getInfoMaterialVOS(), advInfo.getId());
        return Result.success();
    }

    @RequestMapping("delete")
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
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)) {
            menuAdvVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        }
        //配置有效区域ID
        menuAdvVO.setRegionId(infoRegionService.getRegionIds(advInfo.getId()));
        return Result.success(menuAdvVO);
    }


}
