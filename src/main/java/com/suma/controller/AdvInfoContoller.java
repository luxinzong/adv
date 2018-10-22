package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.DefaultExceptionHandler;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvMaterialService;
import com.suma.service.AdvTypeService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.AdvInfoInsertVO;
import com.suma.vo.AdvInfoQueryVO;
import com.suma.vo.AdvInfoUpdateVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping(value = "info",produces = "application/json;charset=utf-8")
public class AdvInfoContoller extends BaseController{

    Logger logger = LoggerFactory.getLogger(AdvInfoContoller.class);
    @Autowired
    private AdvInfoService advInfoService;

    @Autowired
    private InfoMaterialService infoMaterialService;

    @Autowired
    private AdvTypeService advTypeService;

    @Autowired
    private AdvMaterialService advMaterialService;

    /**
     * 查询广告信息
     * @param
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result queryAdvInfo(Integer status,String name,String start,String end,
                               Integer pageNum,Integer pageSize,String advType) throws ParseException {
        if (pageNum == null || pageSize == null || status == null || advType == null) {//检查页码参数和广告状态是否为空
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_QUERYPARAMS_IS_NULL);
        }
        AdvTypeExample example1 = new AdvTypeExample();
        example1.createCriteria().andAdvtypeEqualTo(advType);
        List<AdvType> advTypes = advTypeService.selectByExample(example1);
        PageHelper.startPage(pageNum, pageSize);
        List<Long> list = new ArrayList<>();
        for (AdvType advType1 : advTypes) {
            System.out.println(advType1.getId());
            list.add(advType1.getId());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("status", status);
        map.put("startDate", start);
        map.put("endDate", end);
        map.put("ids", list);
        List<AdvInfo> advInfoList = advInfoService.selectAdvInfo(map);
        PageInfo<AdvInfo> listPageInfo = new PageInfo<>();
        listPageInfo.setList(advInfoList);
        listPageInfo.setTotal(advInfoList.size());
        return Result.success(listPageInfo);
    }

    /**
     * 删除广告信息
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "deleteAdvInfo", method = RequestMethod.POST)
    public Result deleteAdvInfo(Long id) {
        int a = advInfoService.deleteByPK(id);
        InfoMaterialExample example = new InfoMaterialExample();
        example.createCriteria().andAdvInfoIdEqualTo(id);
        int b = infoMaterialService.deleteByExample(example);
        return toResult(a*b);
    }

    /**
     * 批量删除广告信息
     * @param idsStr
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfos(String idsStr) {
        String[] ids = idsStr.substring(0,idsStr.lastIndexOf(",")).split(",");
        int count = 0;
        try {
            if (ids.equals("")) {
                throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
            }
            for (String id : ids) {
                //删除所有广告及所对应资源关系
                advInfoService.deleteByPK(Long.valueOf(id));
                InfoMaterialExample example = new InfoMaterialExample();
                example.createCriteria().andAdvInfoIdEqualTo(Long.valueOf(id));
                infoMaterialService.deleteByExample(example);
                count++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if (count == ids.length) {
            return Result.success();
        } else {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_DELETE_ERROR);
        }
    }


    /**
     * 创建广告信息
     * @param advInfoInsertVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "select", method = RequestMethod.POST)
    public Result insertAdvInfo (AdvInfoInsertVO advInfoInsertVO) throws ParseException {
        System.out.println(advInfoInsertVO);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        AdvInfo advInfo = new AdvInfo();
        advInfo.setStartDate(dateFormat.parse(advInfoInsertVO.getStart()));
        advInfo.setEndDate(dateFormat.parse(advInfoInsertVO.getEnd()));
        advInfo.setPeriodTimeEnd(df.parse(advInfoInsertVO.getPeriodTimeEnd()));
        advInfo.setPeriodTimeStart(df.parse(advInfoInsertVO.getPeriodTimeStart()));
        BeanUtils.copyProperties(advInfoInsertVO, advInfo);
        System.out.println(advInfo);
        if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                || advInfo.getPeriodTimeStart() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        String name = advInfo.getName();
        //检查该名称广告是否存在
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<AdvInfo> advInfoList = advInfoService.selectByExample(example);
        if (advInfoList.size() != 0) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_NAME_IS_EXIT);
        }
        //若不存在,则创建广告信息
        int a = advInfoService.save(advInfo);

        //创建广告信息与广告资源对应表
        List<AdvInfo> advInfos = advInfoService.selectByExample(example);
        //获取广告信息ID
        Long advInfoId = advInfos.get(0).getId();
        //获取广告信息与资源关系表对象
        InfoMaterial infoMaterial = new InfoMaterial();
        //根据前端传递过来的文件名查找对应资源ID
        AdvMaterialExample advMaterialExample = new AdvMaterialExample();
        advMaterialExample.createCriteria().andFileNameEqualTo(advInfoInsertVO.getFileName());
        List<AdvMaterial> advMaterials = advMaterialService.selectByExample(advMaterialExample);
        Long materialId =  advMaterials.get(0).getId();
        infoMaterial.setAdvInfoId(advInfoId);
        infoMaterial.setMaterialId(materialId);
        infoMaterial.setDuration(advInfoInsertVO.getDuration());
        infoMaterial.setSequence(advInfoInsertVO.getSequence());
        int b = infoMaterialService.save(infoMaterial);
        return toResult(a*b);
    }

    /**
     * 编辑广告信息
     * @param advInfoUpdateVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAdvInfo (AdvInfoUpdateVO advInfoUpdateVO) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoUpdateVO, advInfo);
        advInfo.setStartDate(sdf.parse(advInfoUpdateVO.getStart()));
        advInfo.setEndDate(sdf.parse(advInfoUpdateVO.getEnd()));
        advInfo.setPeriodTimeStart(sdf1.parse(advInfoUpdateVO.getPeriodTimeStart()));
        advInfo.setPeriodTimeEnd(sdf1.parse(advInfoUpdateVO.getPeriodTimeEnd()));
        if (advInfo.getId() == null || advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                || advInfo.getPeriodTimeStart() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andIdEqualTo(advInfo.getId());
        if (advInfoService.selectByExample(example).size() == 0) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //更新广告信息
        int a =  advInfoService.update(advInfo);

        //更新广告对应资源表信息
        InfoMaterial infoMaterial = new InfoMaterial();
        BeanUtils.copyProperties(advInfoUpdateVO, infoMaterial);

        //根据文件名查找资源ID
        AdvMaterialExample example1 = new AdvMaterialExample();
        example1.createCriteria().andFileNameEqualTo(advInfoUpdateVO.getFileName());
        Long materialId = advMaterialService.selectByExample(example1).get(0).getId();
        infoMaterial.setMaterialId(materialId);
        InfoMaterialExample example2 = new InfoMaterialExample();
        example2.createCriteria().andMaterialIdEqualTo(materialId).andAdvInfoIdEqualTo(advInfoUpdateVO.getAdvInfoId());
        infoMaterialService.updateByExample(infoMaterial, example2);
        return toResult(1);
    }
}



















