package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * PF条、音量条广告 增删改查 以及根据类型查询所有的广告
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@RestController
@RequestMapping(value = "info")
public class AdvInfoController extends BaseController {
    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private InfoMaterialService infoMaterialService;
    @Autowired
    private AdvInfoServiceGroupService advInfoServiceGroupService;
    @Autowired
    private iAdvRegionService advRegionService;
    @Autowired
    private AdvLocationService advLocationService;
    @Autowired
    private InfoRegionService infoRegionService;


    /**
     * 按照ID查找广告信息
     * @param id 广告信息ID
     * @return Result ->前端显示对象
     */
    @RequestMapping(value = "queryById")
    public Result query(Long id) {
        //创建一个给前端显示的VO对象
        AdvInfoVO advInfoVO = new AdvInfoVO();
        //查询出对应ID的广告信息
        AdvInfo advInfo = advInfoService.findById(id);
        //判断广告信息是否存在
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        if (advInfo.getMaterialType().equals(AdvContants.IMAGE_MATERIAL)|| advInfo.getMaterialType().equals(AdvContants.VEDIO_MATERIAL)) {
            throw new AdvInfoException("PF条仅支持图片广告");
        }
        List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
        //创建一个前端显示资源列表的VO对象
        List<InfoMaterialVO> infoMaterialVOS =infoMaterialService.getInfoMaterialVOS(infoMaterials);
        //将广告资源列表存储到前对显示的广告信息VO对象中
        advInfoVO.setInfoMaterialVOS(infoMaterialVOS);
        //将广告信息存储到前端显示VO对象中
        System.out.println(advInfo);
        BeanUtils.copyProperties(advInfo, advInfoVO);
        System.out.println(advInfoVO);
        //将频道信息查询出来 只有区分频道的广告具有频道分组，其他不区分频道的对所有频道有效
        advInfoServiceGroupService.getServiceGroup(advInfoVO);
        //将区域名称和子名称查询出来
        //所有区域
        advInfoVO.setAdvRegions(advRegionService.selectAdvRegionAll());
        //有效区域ID
        advInfoVO.setRegionId(infoRegionService.getRegionIds(advInfo.getId()));
        //查询出对应的广告位
        advInfoVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        return Result.success(advInfoVO);
    }


    /**
     * 查询广告信息 适用于所有类型广告
     * @param status 广告状态
     * @param name 广告名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageNum 页码数
     * @param pageSize 显示条数
     * @param advTypeId 广告类型
     * @return Result -> 广告信息前端信息
     */
    @RequestMapping(value = "query")
    public Result queryAdvInfo(Integer status, String name, String startDate, String endDate,
                               Integer pageNum, Integer pageSize, String advTypeId) {
        //检查是否缺少必须参数
        if (pageNum == null || pageSize == null || status == null || advTypeId == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_QUERYPARAMS_IS_NULL);
        }
        //将查询参数存入map中
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<AdvInfo> advInfoList = advInfoService.selectAdvInfoByNameAndStatusAndOthor(status, name, startDate, endDate, pageNum, pageSize, advTypeId);
        //将广告信息和查询出来的总数存入PageInfo中返回给前端
        PageInfo<AdvInfo> listPageInfo = new PageInfo<>();
        listPageInfo.setList(advInfoList);
        listPageInfo.setTotal(page.getTotal());
        System.out.println(listPageInfo);
        return Result.success(listPageInfo);
    }

    /**
     * 删除广告信息
     * @param str 广告ID字符串
     * @return Result -> 删除成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete")
    public Result deleteAdvInfos(String str) {
        //判断字符串参数是否为空
        if (StringUtils.isBlank(str)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        //将数组转换成list集合
        List<Long> advInfoIds = StringUtil.convertstr(str);
        advInfoService.deleteAdvRelationInfo(advInfoIds);
        //删除广告对应的频道信息
        advInfoServiceGroupService.deleteAdvServicenByAdvInfoId(advInfoIds);
        return Result.success();
    }

    /**
     * 创建广告信息
     * @param data 前端广告信息VO对象字符串
     * @return Result -> 创建成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "insert")
    public Result insertAdvInfo(String data){
        //将传过来的字符串转换成json对象
        AdvInfoVO advInfoVO = JSON.parseObject(data, AdvInfoVO.class);
        //判断是否缺少参数
        if (advInfoVO.getName() == null || advInfoVO.getStartDate() == null ||
                advInfoVO.getEndDate() == null || advInfoVO.getStatus() == null
                || advInfoVO.getMaterialType() == null || advInfoVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = advInfoVO.getAdvTypeId().equals(AdvContants.CUT_CHANNEL_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加切台广告");
        }
        AdvInfo advInfo = new AdvInfo();
        //将广告信息存入到advInfo中
        BeanUtils.copyProperties(advInfoVO, advInfo);
        advInfo = UserAndTimeUtils.setCreateUserAndTime(advInfo);
        //判断广告信息是否存在
        advInfoService.judgeAdvInfo(advInfo);
        //获取广告位
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation == null) {
            throw new AdvInfoException(AdvContants.LOCATION_IS_NULL);
        }
        advLocationService.save(advLocation);
        advInfo.setAdvLocationId(advLocation.getId());
        //保存广告信息
        advInfoService.save(advInfo);
        //获取广告信息ID
        Long advInfoId = advInfo.getId();
        //获取广告信息与资源关系集合
        List<InfoMaterialVO> infoMaterialVOS = advInfoVO.getInfoMaterialVOS();
        if (CollectionUtils.isEmpty(infoMaterialVOS)) {
            throw new AdvInfoException(AdvContants.MATERIAL_IS_NULL);
        }
        infoMaterialService.saveInfoMaterial(infoMaterialVOS,advInfoId);
        // 如果按照频道进行分组，保存频道信息。
        advInfoServiceGroupService.saveServiceInfomation(advInfoVO, advInfoId);
        //保存region信息
        infoRegionService.saveInfoRegion(advInfoVO.getRegionId(), advInfoId);
        return Result.success();
    }

    /**
     * 编辑广告信息
     * @param data 广告信息字符串
     * @return Result -> 更新成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAdvInfo(String data){
        //将json字符串转换成json对象
        AdvInfoVO advInfoVO = JSON.parseObject(data, AdvInfoVO.class);
        System.out.println(advInfoVO);
        //判断是否缺少参数
        if (advInfoVO.getId() == null || advInfoVO.getName() == null || advInfoVO.getStartDate() == null ||
                advInfoVO.getEndDate() == null || advInfoVO.getStatus() == null
                || advInfoVO.getMaterialType() == null || advInfoVO.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        Boolean flag = advInfoVO.getAdvTypeId().equals(AdvContants.CUT_CHANNEL_ADV_SUBTYPE_ID);
        if (!flag) {
            throw new AdvInfoException("请添加切台广告");
        }
        if (advInfoService.findByPK(advInfoVO.getId()) == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //获取广告位
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation == null) {
            throw new AdvInfoException(AdvContants.LOCATION_IS_NULL);
        }
        //更新广告位信息
        if (advLocation != null) {
            advLocationService.update(advLocation);
        }
        //更新广告信息
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        advInfo = UserAndTimeUtils.setEditUserAndTime(advInfo);
        advInfoService.updateByPrimaryKeySelective(advInfo);
        /* 更新广告对应资源表信息 */
        if (CollectionUtils.isEmpty(advInfoVO.getInfoMaterialVOS())) {
        }
        infoMaterialService.deleteByAdvInfoId(advInfo.getId());
        infoMaterialService.saveInfoMaterial(advInfoVO.getInfoMaterialVOS(),advInfo.getId());
        /* 读取频道信息,更新数据库 */
        advInfoServiceGroupService.deleteAdvServiceByAdvInfoId(advInfo.getId());
        advInfoServiceGroupService.updateServiceGroup(advInfoVO);
        //更新区域信息
        if (CollectionUtils.isEmpty(advInfoVO.getRegionId())) {
            throw new AdvInfoException(AdvContants.REGION_ID_IS_NULL);
        }
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        infoRegionService.saveInfoRegion(advInfoVO.getRegionId(),advInfo.getId());
        return Result.success();
    }

    /**
     * 获取视频源接口（模拟）
     * @return
     */
    @RequestMapping(value = "getVedioMaterial")
    public Result getVedioMaterial() {
        List<AdvMaterial> list = advInfoService.getVedioMaterial();
        return Result.success(list);
    }

    //TODO 提交审核接口
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("submit")
    public Result submitCheck(String data) {
        List<Long> list = StringUtil.convertstr(data);
        list.forEach(id ->{
            AdvInfo advInfo = advInfoService.findByPK(id);
            if (advInfo != null) {
                advInfo.setStatus(AdvContants.STATUS_WAIT_CHECK);
                advInfoService.updateByPrimaryKeySelective(advInfo);
            }
        });
        return Result.success();
    }
}



















