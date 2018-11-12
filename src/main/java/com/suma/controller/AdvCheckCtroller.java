package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvCheckException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.service.impl.AdvRegionService;
import com.suma.utils.Result;
import com.suma.utils.ShiroUtils;
import com.suma.utils.StringUtil;
import com.suma.vo.CheckListVO;
import com.suma.vo.CheckDetailVO;
import com.suma.vo.CheckMaterialVO;
import com.suma.vo.DoCheckVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Collections.sort;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/11/08
 * @description:
 */

@RestController
@RequestMapping(value = "check")
public class AdvCheckCtroller extends BaseController {
    @Autowired
    private AdvCheckService advCheckService;
    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private AdvFlywordService advFlywordService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private InfoFlywordService infoFlywordService;
    @Autowired
    private AdvTypeService advTypeService;
    @Autowired
    private AdvInfoServiceGroupService advInfoServiceGroupService;
    @Autowired
    private ServiceGroupService serviceGroupService;
    @Autowired
    private InfoRegionService infoRegionService;
    @Autowired
    private AdvRegionService advRegionService;
    @Autowired
    private AdvMaterialService advMaterialService;
    @Autowired
    private InfoMaterialService infoMaterialService;

    @RequestMapping("queryAll")
    public Result queryAll(Integer pageNum, Integer pageSize, Integer status, Integer region) {
        if (pageNum == null || pageSize == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        AdvInfoExample advInfoExample = new AdvInfoExample();
        AdvInfoExample.Criteria advCriteria = advInfoExample.createCriteria();
        advCriteria.andStatusNotEqualTo(1);
        if (status != null) {
            advCriteria.andStatusEqualTo(status);
        }
        if (region != null) {
            List<Long> regionAdvIds = infoRegionService.selectAdvByRegion(region);
            advCriteria.andIdIn(regionAdvIds);
        }
        advInfoExample.setOrderByClause("STATUS asc,LAST_EDIT_TIME desc");

        PageHelper.startPage(pageNum, pageSize);
        List<AdvInfo> advInfos = advInfoService.selectByExample(advInfoExample);

        PageInfo<AdvInfo> pageInfo = new PageInfo<>(advInfos);
        PageInfo<CheckListVO> resultPage = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, resultPage);

        List<CheckListVO> checkVOS = new ArrayList<>();
        for (AdvInfo advInfo : advInfos) {
            CheckListVO advCheckVO = new CheckListVO();
            BeanUtils.copyProperties(advInfo, advCheckVO);
            advCheckVO.setAdvName(advInfo.getName());
            advCheckVO.setAdvInfoId(advInfo.getId());
            AdvType advType = advTypeService.findByPK(advInfo.getAdvTypeId());
            BeanUtils.copyProperties(advType, advCheckVO);
            checkVOS.add(advCheckVO);
        }
        resultPage.setList(checkVOS);

        return Result.success(resultPage);
    }

    @RequestMapping("query")
    public Result query(Long advInfoId) {
        if (advInfoId == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        CheckDetailVO checkDetailVO = new CheckDetailVO();
        AdvInfo advInfo = advInfoService.findByPK(advInfoId);

        //填入基本广告信息
        checkDetailVO.setAdvId(advInfoId);
        checkDetailVO.setAdvName(advInfo.getName());
        BeanUtils.copyProperties(advInfo, checkDetailVO);
        AdvType advType = advTypeService.findByPK(advInfo.getAdvTypeId());
        BeanUtils.copyProperties(advType, checkDetailVO);

        //填入位置信息
        AdvLocation location = advLocationService.findByPK(advInfo.getAdvLocationId());
        checkDetailVO.setLocation(location);

        //字幕
        if (advInfo.getMaterialType().equals(AdvContants.TEXT_MATERIAL)) {
            List<Long> flywordIds = infoFlywordService.selectFlywordIds(advInfoId);
            List<AdvFlyWord> advFlyWords = advFlywordService.findList(flywordIds);
            checkDetailVO.setFlyWords(advFlyWords);
        }
        //图片视频
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)
                || advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
            List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfoId);
            List<CheckMaterialVO> checkMaterialVOS = new ArrayList<>();
            for (InfoMaterial infoMaterial : infoMaterials) {
                AdvMaterial material = advMaterialService.findByPK(infoMaterial.getMaterialId());
                CheckMaterialVO materialVO = new CheckMaterialVO();
                materialVO.setSequence(infoMaterial.getSequence());
                materialVO.setDuration(infoMaterial.getDuration());
                BeanUtils.copyProperties(material, materialVO);
                checkMaterialVOS.add(materialVO);
            }

            if (checkMaterialVOS.size() >= 2) {
                sort(checkMaterialVOS, Comparator.comparingInt(CheckMaterialVO::getSequence));
            }
            checkDetailVO.setMaterials(checkMaterialVOS);
        }


        //区域
        List<String> regionNames = infoRegionService.getRegionNames(advInfoId);
        checkDetailVO.setRegionNames(regionNames);

        //填入频道关联状态字段
        checkDetailVO.setServiceStatus(Math.toIntExact(advInfo.getReservedInt()));
        //填入绑定频道分组
        if (advInfo.getReservedInt().equals(AdvContants.ADV_SERVICE_ASSOCIATE)) {
            List<ServiceGroup> groups = advInfoServiceGroupService.findGroupNamesByAdvId(advInfoId);
            List<String> groupNames = new ArrayList<>();
            for (ServiceGroup group : groups) {
                groupNames.add(group.getGroupName());
            }
            checkDetailVO.setServiceGroupNames(groupNames);
            checkDetailVO.setServiceType(groups.get(0).getType());
        }
        //填入CA
        checkDetailVO.setCA(advInfo.getReservedString());

        return Result.success(checkDetailVO);
    }

    @RequestMapping("doCheck")
    public Result check(@Validated DoCheckVO doCheckVO) {
        AdvInfo advInfo = advInfoService.findByPK(doCheckVO.getAdvId());
        advInfo.setCheckNote(doCheckVO.getCheckNote());
        advInfo.setStatus(doCheckVO.getStatus());
        advInfo.setCheckTime(new Date());
        advInfo.setCheckUser(ShiroUtils.getUser().getUserName());
        return toResult(advInfoService.update(advInfo));
    }

    /**
     * 批量删除或免审 审核信息
     *
     * @param str 审核信息ID字符串
     * @return 返回更新结果 Result 删除成功或失败
     */
    @RequestMapping(value = "delete")
    public Result delete(String str, Integer status) {
        //判断id字符串是否为空
        if (StringUtils.isBlank(str) || status == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //将id字符串转解析成字符串数组，广告id
        List<Long> idsList = StringUtil.convertstr(str);
        for (Long id : idsList) {
            AdvInfo advInfo = advInfoService.findByPK(id);
            advInfo.setStatus(status);
            AdvCheckDetail advCheckDetail = advCheckService.select(advInfo.getId());
            if (status == AdvContants.STATUS_EDIT) {
                advCheckService.deleteAll((Long[]) idsList.toArray());
            } else if (status == AdvContants.STATUS_PASS) {
                if (advCheckDetail != null) {
                    advCheckDetail.setMark(AdvContants.STATUS_PASS_DSC);
                }
            }
            advInfoService.update(advInfo);
        }
        return Result.success();
    }
}
