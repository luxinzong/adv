package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.ShiroUtils;
import com.suma.utils.StringUtil;
import com.suma.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 广告信息 增删改查
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
    private AdvMaterialService advMaterialService;
    @Autowired
    private AdvFlywordService advFlywordService;
    @Autowired
    private InfoFlywordService infoFlywordService;
    @Autowired
    private AdvInfoServiceGroupService advInfoServiceGroupService;
    @Autowired
    private ServiceGroupService serviceGroupService;
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
        if (advInfo.getMaterialType() == 1) {
            advInfoVO.setAdvFlyWords(infoFlywordService.getAdvFlyWords(advInfo.getId()));
        }
        if (advInfo.getMaterialType() == 2 || advInfo.getMaterialType() == 3) {
            List<InfoMaterial> infoMaterials = infoMaterialService.findByAdv(advInfo.getId());
            //创建一个前端显示资源列表的VO对象
            List<InfoMaterialVO> infoMaterialVOS =infoMaterialService.getInfoMaterialVOS(infoMaterials);
            //将广告资源列表存储到前对显示的广告信息VO对象中
            advInfoVO.setInfoMaterialVOS(infoMaterialVOS);
        }
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
        advInfoVO.setRegionIds(infoRegionService.getRegionIds(advInfo.getId()));
        //查询出对应的广告位
        boolean flag  = !((advInfo.getAdvTypeId() == 2 || advInfo.getAdvTypeId() == 9 ) && advInfo.getMaterialType() == 3);
        if (flag) {
            advInfoVO.setAdvLocation(advLocationService.findByPK(advInfo.getAdvLocationId()));
        }
        return Result.success(advInfoVO);
    }


    /**
     * 查询广告信息
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
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("status", status);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        PageInfo<AdvInfo> listPageInfo = new PageInfo<>();
        map.put("advTypeId",advTypeId);
        //根据map查询出广告信息
        List<AdvInfo> advInfoList = advInfoService.selectAdvInfo(map);
        //将广告信息和查询出来的总数存入PageInfo中返回给前端
        listPageInfo.setList(advInfoList);
        listPageInfo.setTotal(advInfoList.size());
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
        //删除广告信息
        advInfoService.deleteByAdvInfoIds(advInfoIds);
        //删除广告对应资源信息
        infoMaterialService.deleteByAdvInfoIds(advInfoIds);
        //删除字幕广告对应列表
        infoFlywordService.deleteByAdvInfoIds(advInfoIds);
        //删除广告对应的频道信息
        infoRegionService.deleteByAdvInfoIds(advInfoIds);
        //删除广告对应区域信息
        infoRegionService.deleteByAdvInfoIds(advInfoIds);
        //删除广告位
        deleteAdvLocation(advInfoIds);
        return Result.success();
    }
    /**
     * 删除广告位
     * @param advInfoIds
     */
    public void deleteAdvLocation(List<Long> advInfoIds) {
        List<Long> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(id ->{
                if (advInfoService.findById(id) != null) {
                    list.add(advInfoService.findById(id).getAdvLocationId());
                }
            });
        }
        if (!CollectionUtils.isEmpty(list)) {
            AdvLocationExample example = new AdvLocationExample();
            example.createCriteria().andIdIn(list);
            advLocationService.deleteByExample(example);
        }
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
        //将广告信息存入到advInfo中
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        //判断是否缺少参数
        if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        advInfo.setCreatedUser(ShiroUtils.getLoginName());
        System.out.println(ShiroUtils.getLoginName()+"哈哈");
        advInfo.setCreatedTime(new Date());
        advInfo.setLastEditUser(ShiroUtils.getLoginName());
        advInfo.setLastEditModule("");//TODO 最近编辑模块
        //判断广告信息是否存在
        advInfoService.judgeAdvInfo(advInfo);
        //获取广告位
        AdvLocation advLocation = advInfoVO.getAdvLocation();
        if (advLocation != null) {
            advLocationService.save(advLocation);
            advInfo.setAdvLocationId(advLocation.getId());
        } else {
            //开机广告
            advInfo.setAdvLocationId(AdvContants.START_LOGO_ADV_LOCATION_ID);
        }
        //保存广告信息
        advInfoService.save(advInfo);
        //获取广告信息ID
        Long advInfoId = advInfo.getId();
        //获取广告信息与资源关系集合
        List<InfoMaterialVO> infoMaterialVOS = advInfoVO.getInfoMaterialVOS();
        System.out.println(infoMaterialVOS);
        infoMaterialService.saveInfoMaterial(infoMaterialVOS,advInfoId);
        //获取字幕广告信息,保存字幕广告信息
        List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
        infoFlywordService.saveFlyWords(advFlyWords,advInfoId);
        // 如果按照频道进行分组，保存频道信息。
        advInfoServiceGroupService.saveServiceInfomation(advInfoVO, advInfoId);
        //保存region信息
        infoRegionService.saveInfoRegion(advInfoVO.getRegionIds(), advInfoId);
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
        AdvInfoVO advInfoUpdateVO = JSON.parseObject(data, AdvInfoVO.class);
        System.out.println(advInfoUpdateVO);
        //将前端对象传递过来的信息存入advInfo对应的属性中
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoUpdateVO, advInfo);
        System.out.println(advInfo);
        //判断是否缺少参数
        if (advInfo.getId() == null || advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (advInfoService.findByPK(advInfo.getId()) == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //获取广告位
        AdvLocation advLocation = advInfoUpdateVO.getAdvLocation();
        //更新广告位信息
        if (advLocation != null) {
            advLocationService.update(advInfoUpdateVO.getAdvLocation());
        }
        //更新广告信息
        advInfo.setLastEditUser(ShiroUtils.getLoginName());
        advInfo.setLastEditModule("");//TODO 最近编辑模块
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新广告对应资源表信息
        //将前端传过来的广告资源数据复制到资源关系对象中 advInfoUpdateVO -> infoMaterial中
        //获得前端资源关系VO对象列表
        infoMaterialService.deleteByAdvInfoId(advInfo.getId());
        infoMaterialService.saveInfoMaterial(advInfoUpdateVO.getInfoMaterialVOS(),advInfo.getId());
        //更新字幕广告信息
        //从updateVO中获得字幕广告信息
        infoFlywordService.deletByAdvInfoId(advInfo.getId());
        List<AdvFlyWord> advFlyWords = advInfoUpdateVO.getAdvFlyWords();
       infoFlywordService.saveFlyWords(advFlyWords,advInfo.getId());
        //读取频道信息,更新数据库 //TODO 更新频道信息
        advInfoServiceGroupService.deleteAdvRegionByAdvInfoId(advInfo.getId());
        advInfoServiceGroupService.updateServiceGroup(advInfoUpdateVO);
        //更新区域信息
        infoRegionService.deleteByAdvInfoId(advInfo.getId());
        infoRegionService.saveInfoRegion(advInfoUpdateVO.getRegionIds(),advInfo.getId());
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
            AdvInfo advInfo = new AdvInfo();
            advInfo.setCreatedUser(ShiroUtils.getLoginName());
            advInfo.setCreatedTime(new Date());
            advInfo.setLastEditUser(ShiroUtils.getLoginName());
            advInfo.setLastEditModule("");//TODO 最近编辑模块
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
        AdvInfo advInfo = new AdvInfo();
        advInfo.setLastEditModule("");
        advInfo.setLastEditUser(ShiroUtils.getLoginName());
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
        infoMaterialService.saveInfoMaterial(menuAdvVO.getInfoMaterialVOS(),advInfo.getId());
        return Result.success();
    }

/*    @RequestMapping("deleteMenuAdv")
    public Result deleteMenuAdv(String str) {
        List<Long> advInfoIds = StringUtil.convertstr(str);
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(advInfoId->{
                //删除广告信息
                advInfoService.deleteByPK(advInfoId);
                //删除广告位
                advLocationService.deleteByPK(advInfoService.findByPK(advInfoId).getAdvLocationId());
                //删除有效区域
                infoRegionService.deleteByAdvInfoId(advInfoId);
                //删除对应关系
                infoMaterialService.deleteByAdvInfoId(advInfoId);
            });
        }
        return Result.success();
    }

    @RequestMapping("deleteNetAdv")
    public Result deleteNetAdv(String str) {
        List<Long> advInfoIds = StringUtil.convertstr(str);
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(advInfoId->{
                //删除广告信息
                advInfoService.deleteByPK(advInfoId);
                //删除广告位
                advLocationService.deleteByPK(advInfoService.findByPK(advInfoId).getAdvLocationId());
                //删除有效区域
                infoRegionService.deleteByAdvInfoId(advInfoId);
                //删除对应资源关系表
                infoMaterialService.deleteByAdvInfoId(advInfoId);
            });
        }
        return Result.success();
    }*/

    /**
     * 更新植入广告
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("updateNetAdv")
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
        //更新广告信息
        AdvInfo advInfo = new AdvInfo();
        advInfo.setLastEditUser(ShiroUtils.getLoginName());
        advInfo.setLastEditModule("");//TODO 最近编辑模块
        BeanUtils.copyProperties(netAdvVO, advInfo);
        advInfoService.updateByPrimaryKeySelective(advInfo);
        //更新位置信息
        AdvLocation advLocation = netAdvVO.getAdvLocation();
        if (advInfo.getMaterialType() == 2) {
            if (advLocation == null) {
                throw new AdvInfoException("广告位信息未填写");
            }
            advLocationService.update(advLocation);
        }
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
        //获取视频源Id
        List<Long> list = netAdvVO.getVideoId();
        System.out.println(list);
        return Result.success();
    }

    /**
     * 植入广告添加接口
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("netAdv")
    public Result saveNetAdv(String data) {
        NetAdvVO netAdvVO = JSON.parseObject(data, NetAdvVO.class);
        if (netAdvVO == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        System.out.println(netAdvVO.getRegionId());
        if (netAdvVO.getName() == null || netAdvVO.getStartDate() == null ||
                netAdvVO.getEndDate() == null || netAdvVO.getStatus() == null
                || netAdvVO.getMaterialType() == null || netAdvVO.getAdvTypeId() == null || netAdvVO.getVideoId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        AdvInfo advInfo = new AdvInfo();
        advInfo.setLastEditModule("");
        advInfo.setLastEditUser(ShiroUtils.getLoginName());
        advInfo.setCreatedTime(new Date());
        advInfo.setCreatedUser(ShiroUtils.getLoginName());
        BeanUtils.copyProperties(netAdvVO, advInfo);
        AdvLocation advLocation = netAdvVO.getAdvLocation();
        System.out.println(advLocation);
        //保存位置信息
        if (advInfo.getMaterialType() == 2) {
            if (advLocation != null) {
                advLocationService.save(advLocation);
                //保存广告信息
                advInfo.setAdvLocationId(advLocation.getId());
            }
        }
        advInfoService.save(advInfo);
        //保存区域
        if (CollectionUtils.isEmpty(netAdvVO.getRegionId())) {
            throw new AdvInfoException("未填写区域信息");
        }
        infoRegionService.saveInfoRegion(netAdvVO.getRegionId(),advInfo.getId());
        //保存资源关系列表
        List<InfoMaterialVO> infoMaterialVOS = netAdvVO.getInfoMaterialVOS();
        if (CollectionUtils.isEmpty(infoMaterialVOS)) {
            throw new AdvInfoException("未填写资源信息列表");
        }
        infoMaterialService.saveInfoMaterial(infoMaterialVOS,advInfo.getId());

        //获取视频源Id
        List<Long> list = netAdvVO.getVideoId();
        System.out.println(list);
        return Result.success();
    }
}



















