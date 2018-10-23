package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.exception.AdvMaterialException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvMaterialService;
import com.suma.service.AdvTypeService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.AdvInfoInsertVO;
import com.suma.vo.AdvInfoUpdateVO;
import com.suma.vo.AdvMaterialVO;
import com.suma.vo.InfoMaterialVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 广告信息 增删改查
 *
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


    /**
     * 按照ID查找广告信息
     * @param id
     * @return
     */
    @RequestMapping(value = "queryById")
    public Result query(Long id) {
        return Result.success(advInfoService.findById(id));
    }

    /**
     * 查询广告信息
     *
     * @param
     * @return
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
        //获取广告类型ID并存入集合中
        List<Long> list = new ArrayList<>();
        for (AdvType advType1 : advTypes) {
            System.out.println(advType1.getId());
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
        } else {
            List<AdvInfo> advInfoList = new ArrayList<AdvInfo>();
            listPageInfo.setList(advInfoList);
        }
        return Result.success(listPageInfo);
    }

    /**
     * 删除广告信息
     *
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfo(Long id) {
        System.out.println(id);
        int a = advInfoService.deleteByPK(id);
        InfoMaterialExample example = new InfoMaterialExample();
        example.createCriteria().andAdvInfoIdEqualTo(id);
        if ( infoMaterialService.selectByExample(example).size() != 0) {
            infoMaterialService.deleteByExample(example);
        }
        return Result.success();
    }

    /**
     * 批量删除广告信息
     *
     * @param idsStr
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result deleteAdvInfos(String idsStr) {
        String[] ids = idsStr.substring(0, idsStr.lastIndexOf(",")).split(",");
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
                if ( infoMaterialService.selectByExample(example).size() != 0) {
                    infoMaterialService.deleteByExample(example);
                }
                count++;
            }
        } catch (Exception e) {
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
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "insert")
    public Result insertAdvInfo(String data) throws ParseException {
        AdvInfoInsertVO advInfoInsertVO = JSON.parseObject(data, AdvInfoInsertVO.class);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("HH:mm");
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
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
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
        List<InfoMaterialVO> infoMaterialVOS = advInfoInsertVO.getInfoMaterialVOS();

        //根据前端传递过来的文件名查找对应资源ID
        AdvMaterialExample example1 = new AdvMaterialExample();
        int count = 0;
        for (InfoMaterialVO infoMaterialVO : infoMaterialVOS) {
            example1.createCriteria().andFileNameEqualTo(infoMaterialVO.getFileName());
            List<AdvMaterial> advMaterials = advMaterialService.selectByExample(example1);
            if (advMaterials == null) {
                throw new AdvMaterialException(ExceptionConstants.ADV_MATERIAL_IS_NULL);
            }
            Long advMaterialId = advMaterials.get(0).getId();
            InfoMaterial infoMaterial = new InfoMaterial();
            infoMaterial.setMaterialId(advMaterialId);
            infoMaterial.setAdvInfoId(advInfoId);
            BeanUtils.copyProperties(infoMaterialVO, infoMaterial);
            int b = infoMaterialService.save(infoMaterial);
            if (b > 0) {
                count++;
            }
        }
        if (count == infoMaterialVOS.size() && a > 0) {
            return Result.success();
        } else {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INSERT_FAIL);
        }
    }

    /**
     * 编辑广告信息
     *
     * @param data
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result updateAdvInfo(String data) throws ParseException {
        AdvInfoUpdateVO advInfoUpdateVO = JSON.parseObject(data, AdvInfoUpdateVO.class);
        System.out.println(advInfoUpdateVO);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        AdvInfo advInfo = new AdvInfo();
        BeanUtils.copyProperties(advInfoUpdateVO, advInfo);
        advInfo.setId(advInfoUpdateVO.getAdvInfoId());
        advInfo.setStartDate(sdf.parse(advInfoUpdateVO.getStart()));
        advInfo.setEndDate(sdf.parse(advInfoUpdateVO.getEnd()));
        advInfo.setPeriodTimeStart(sdf1.parse(advInfoUpdateVO.getPeriodTimeStart()));
        advInfo.setPeriodTimeEnd(sdf1.parse(advInfoUpdateVO.getPeriodTimeEnd()));
        System.out.println(advInfo);
        if (advInfo.getId() == null || advInfo.getName() == null || advInfo.getStartDate() == null ||
                advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                || advInfo.getPeriodTimeStart() == null || advInfo.getStatus() == null
                || advInfo.getMaterialType() == null || advInfo.getAdvTypeId() == null) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
        }
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andIdEqualTo(advInfo.getId());
        if (advInfoService.selectByExample(example).size() == 0) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_IS_NOT_EXIT);
        }
        //更新广告信息
        int a = advInfoService.update(advInfo);
        //更新广告对应资源表信息
        //将前端传过来的数据复制到pojo中 advInfoUpdateVO -> infoMaterial中
        AdvMaterialExample example1 = new AdvMaterialExample();
        List<InfoMaterialVO> infoMaterialVOS = advInfoUpdateVO.getInfoMaterialVOS();
        int count = 0;
        for (InfoMaterialVO infoMaterialVO : infoMaterialVOS) {
            example1.createCriteria().andFileNameEqualTo(infoMaterialVO.getFileName());
            List<AdvMaterial> advMaterials = advMaterialService.selectByExample(example1);
            if (advMaterials == null) {
                throw new AdvMaterialException(ExceptionConstants.ADV_MATERIAL_IS_NULL);
            }
            Long advMaterialId = advMaterials.get(0).getId();
            InfoMaterial infoMaterial = new InfoMaterial();
            infoMaterial.setMaterialId(advMaterialId);
            infoMaterial.setAdvInfoId(advInfo.getId());
            BeanUtils.copyProperties(infoMaterialVO, infoMaterial);
            InfoMaterialExample example2 = new InfoMaterialExample();
            example2.createCriteria().andMaterialIdEqualTo(advMaterialId).andAdvInfoIdEqualTo(advInfoUpdateVO.getAdvInfoId());
            int b = infoMaterialService.updateByExample(infoMaterial, example2);
            if (b > 0) {
                count++;
            }
        }
        if (count == infoMaterialVOS.size() && a > 0) {
            return Result.success(advInfoUpdateVO);
        } else {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INSERT_FAIL);
        }
    }
}



















