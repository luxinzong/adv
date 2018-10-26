package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 广告信息 增删改查
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@RestController
@RequestMapping(value = "info", produces = "application/json;charset=utf-8")
public class AdvInfoContoller extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AdvInfoContoller.class);

    @Autowired
    private AdvInfoService advInfoService;

    @Autowired
    private InfoMaterialService infoMaterialService;

    @Autowired
    private AdvTypeService advTypeService;

    @Autowired
    private AdvMaterialService advMaterialService;

    @Autowired
    private AdvFlywordService advFlywordService;

    @Autowired
    private InfoFlywordService infoFlywordService;


    /**
     * 按照ID查找广告信息
     * @param id 广告信息ID
     * @return Result ->前端显示对象
     */
    @RequestMapping(value = "queryById")
    public Result query(Long id) {
        //创建一个给前端显示的VO对象
        AdvInfoVO advInfoVO = new AdvInfoVO();
        //根据广告ID查询出对应的广告资源列表
        InfoMaterialExample example = new InfoMaterialExample();
        example.createCriteria().andAdvInfoIdEqualTo(id);
        List<InfoMaterial> infoMaterials = infoMaterialService.selectByExample(example);
        //创建一个前端显示资源列表的VO对象
        List<InfoMaterialVO> infoMaterialVOS = new ArrayList<>();
        //将查询到的资源信息存储到前端资源信息VO对象中
        for (InfoMaterial infoMaterial : infoMaterials) {
            //判断资源是否存在，如果不存在提示用户资源不存在
            if (advMaterialService.findByPK(infoMaterial.getMaterialId()) == null) {
                throw new AdvMaterialException(ExceptionConstants.ADV_MATERIAL_IS_NULL);
            }
            //查找对应资源文件名称
            String fileName = advMaterialService.findByPK(infoMaterial.getMaterialId()).getFileName();
            //创建前对资源信息VO对象，将资源信息存储到VO对象中
            InfoMaterialVO infoMaterialVO = new InfoMaterialVO();
            BeanUtils.copyProperties(infoMaterial,infoMaterialVO);
            infoMaterialVO.setFileName(fileName);
            //将前端资源信息VO对象存储到信息列表中
            infoMaterialVOS.add(infoMaterialVO);
        }
        //创建字幕广告对象
        //根据广告ID查询出对应的字幕广告ID
        List<Long> flywordIds = infoFlywordService.selectFlywordIds(id);
        AdvFlyWordExample example1 = new AdvFlyWordExample();
        List<AdvFlyWord> advFlyWords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(flywordIds)) {
            example1.createCriteria().andIdIn(flywordIds);
            System.out.println(flywordIds+"阿古斯八卦");
            advFlyWords = advFlywordService.selectByExample(example1);
        }
        //查询出对应ID的广告信息
        AdvInfo advInfo = advInfoService.findById(id);
        //判断广告信息是否存在
        if (advInfo == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //将广告信息存储到前端显示VO对象中
        System.out.println(advInfo);
        BeanUtils.copyProperties(advInfo, advInfoVO);
        System.out.println(advInfoVO);
        //将广告资源列表存储到前对显示的广告信息VO对象中
        advInfoVO.setInfoMaterialVOS(infoMaterialVOS);
        //将字幕广告信息存储到广告信息VO对象中
        advInfoVO.setAdvFlyWords(advFlyWords);
        return Result.success(advInfoVO);
    }

    /**
     * 查询广告信息
     * @param status 广告状态
     * @param name 广告名称
     * @param start 开始日期
     * @param end 结束日期
     * @param pageNum 页码数
     * @param pageSize 显示条数
     * @param advType 广告类型
     * @return Result -> 广告信息前端信息
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result queryAdvInfo(Integer status, String name, String start, String end,
                               Integer pageNum, Integer pageSize, String advType) {
        //检查是否缺少必须参数
        if (pageNum == null || pageSize == null || status == null || advType == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_QUERYPARAMS_IS_NULL);
        }
        //根据广告类型查询广告类型ID
        AdvTypeExample example1 = new AdvTypeExample();
        example1.createCriteria().andAdvtypeEqualTo(advType);
        List<AdvType> advTypes = advTypeService.selectByExample(example1);
        //进行分页查询
        PageHelper.startPage(pageNum, pageSize);
        //获取广告类型ID并存入List集合中
        List<Long> list = new ArrayList<>();
        for (AdvType advType1 : advTypes) {
            list.add(advType1.getId());
        }
        //将查询参数存入map中
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("status", status);
        map.put("startDate", start);
        map.put("endDate", end);
        PageInfo<AdvInfo> listPageInfo = new PageInfo<>();
        if (!CollectionUtils.isEmpty(list)) {
            map.put("ids", list);
            //根据map查询出广告信息
            List<AdvInfo> advInfoList = advInfoService.selectAdvInfo(map);
            //将广告信息和查询出来的总数存入PageInfo中返回给前端
            listPageInfo.setList(advInfoList);
            listPageInfo.setTotal(advInfoList.size());
        }
        System.out.println(listPageInfo);
        return Result.success(listPageInfo);
    }

    /**
     * 删除广告信息
     * @param str 广告ID字符串
     * @return Result -> 删除成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfos(String str) {
        //判断字符串参数是否为空
        if (StringUtils.isBlank(str)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        //将数组转换成list集合
        List<Long> advInfoIds = StringUtil.convertstr(str);
        //删除广告信息
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andIdIn(advInfoIds);
        advInfoService.deleteByExample(example);
        //删除广告对应资源信息
        InfoMaterialExample example1 = new InfoMaterialExample();
        example1.createCriteria().andAdvInfoIdIn(advInfoIds);
        //判断广告资源是否存在，若存在则删除
        if ( infoMaterialService.selectByExample(example1).size() != 0) {
            infoMaterialService.deleteByExample(example1);
        }
        //删除字幕广告对应列表
        InfoFlyWordExample example2 = new InfoFlyWordExample();
        example2.createCriteria().andAdvInfoIdIn(advInfoIds);
        infoFlywordService.deleteByExample(example2);
        return Result.success();
    }

    /**
     * 创建广告信息
     * @param data 前端广告信息VO对象字符串
     * @return Result -> 创建成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "insert")
    public Result insertAdvInfo(String data) throws ParseException {
        //将传过来的字符串转换成json对象
        AdvInfoVO advInfoVO = JSON.parseObject(data, AdvInfoVO.class);
        //将广告信息存入到advInfo中
        System.out.println(advInfoVO);
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoVO, advInfo);
        System.out.println(advInfo);
        //判断是否缺少参数
        if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                || advInfo.getPeriodTimeStart() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        String name = advInfo.getName();
        //检查该名称广告是否存在，如果存在提示用户该广告名称已经存在
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<AdvInfo> advInfoList = advInfoService.selectByExample(example);
        if (advInfoList.size() != 0) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_NAME_IS_EXIT);
        }
        //若不存在,则创建广告信息
        advInfoService.save(advInfo);
        //创建广告信息与广告资源对应表
        List<AdvInfo> advInfos = advInfoService.selectByExample(example);
        //获取广告信息ID
        Long advInfoId = advInfos.get(0).getId();
        //获取广告信息与资源关系表对象
        List<InfoMaterialVO> infoMaterialVOS = advInfoVO.getInfoMaterialVOS();
        //根据前端传递过来的文件名查找对应资源ID，根据资源ID和广告信息ID更新广告资源关系列表
        AdvMaterialExample example1 = new AdvMaterialExample();
        for (InfoMaterialVO infoMaterialVO : infoMaterialVOS) {
            //根据文件名查找对应资源
            example1.createCriteria().andFileNameEqualTo(infoMaterialVO.getFileName());
            List<AdvMaterial> advMaterials = advMaterialService.selectByExample(example1);
            //如果资源存在则添加到广告资源关系列表
            if (advMaterials != null) {
                //获取广告资源ID
                Long advMaterialId = advMaterials.get(0).getId();
                //将广告信息资源对应关系信息存储到关系对象中
                InfoMaterial infoMaterial = new InfoMaterial();
                infoMaterial.setMaterialId(advMaterialId);
                infoMaterial.setAdvInfoId(advInfoId);
                BeanUtils.copyProperties(infoMaterialVO, infoMaterial);
                infoMaterialService.save(infoMaterial);
            }
        }
        //获取字幕广告信息,保存字幕广告信息
        List<AdvFlyWord> advFlyWords = advInfoVO.getAdvFlyWords();
        System.out.println(advFlyWords);
        if (!CollectionUtils.isEmpty(advFlyWords)) {
            for (AdvFlyWord advFlyWord : advFlyWords) {
                if (advFlyWord.getId() == null) {
                    advFlywordService.save(advFlyWord);
                }
                InfoFlyWord infoFlyWord = new InfoFlyWord();
                infoFlyWord.setFlywordId(advFlyWord.getId());
                infoFlyWord.setAdvInfoId(advInfoId);
                infoFlywordService.save(infoFlyWord);
            }
        }
        return Result.success();
    }

    /**
     * 编辑广告信息
     * @param data 广告信息字符串
     * @return Result -> 更新成功或失败
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAdvInfo(String data) throws ParseException {
        //将json字符串转换成json对象
        AdvInfoVO advInfoUpdateVO = JSON.parseObject(data, AdvInfoVO.class);
        System.out.println(advInfoUpdateVO);
        //将前端对象传递过来的信息存入advInfo对应的属性中
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoUpdateVO, advInfo);
        System.out.println(advInfo);
        //判断是否缺少参数
        if (advInfo.getId() == null || advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                || advInfo.getPeriodTimeStart() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        if (advInfoService.findByPK(advInfo.getId()) == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //更新广告信息
        advInfoService.update(advInfo);
        //更新广告对应资源表信息
        //将前端传过来的广告资源数据复制到资源关系对象中 advInfoUpdateVO -> infoMaterial中
        //获得前端资源关系VO对象列表
        List<InfoMaterialVO> infoMaterialVOS = advInfoUpdateVO.getInfoMaterialVOS();
        for (InfoMaterialVO infoMaterialVO : infoMaterialVOS) {
            //根据资源名称查找对应资源
            AdvMaterialExample example1 = new AdvMaterialExample();
            example1.createCriteria().andFileNameEqualTo(infoMaterialVO.getFileName());
            List<AdvMaterial> advMaterials = advMaterialService.selectByExample(example1);
            //如果资源存在，则更新对应资源关系信息
            if (advMaterials != null) {
                Long advMaterialId = advMaterials.get(0).getId();
                InfoMaterial infoMaterial = new InfoMaterial();
                infoMaterial.setMaterialId(advMaterialId);
                infoMaterial.setAdvInfoId(advInfo.getId());
                BeanUtils.copyProperties(infoMaterialVO, infoMaterial);
                infoMaterialService.updateByDoubleId(infoMaterial);
            }
        }
        //更新字幕广告信息
        //从updateVO中获得字幕广告信息
        List<AdvFlyWord> advFlyWords = advInfoUpdateVO.getAdvFlyWords();
        if (!CollectionUtils.isEmpty(advFlyWords)) {
            //把ID存起来
            List<Long> ids = new  ArrayList<>();
            for (AdvFlyWord advFlyWord : advFlyWords) {
                if (advFlyWord.getId() == null) {
                    advFlywordService.save(advFlyWord);
                }
                ids.add(advFlyWord.getId());
            }
            for (Long id : ids) {
                InfoFlyWord infoFlyWord = new InfoFlyWord();
                infoFlyWord.setAdvInfoId(advInfo.getId());
                infoFlyWord.setFlywordId(id);
                infoFlywordService.save(infoFlyWord);
            }
        }

        return Result.success();
    }
}



















