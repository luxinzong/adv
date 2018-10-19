package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.AdvMaterialService;
import com.suma.service.AdvTypeService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.AdvInfoInsertVO;
import com.suma.vo.AdvInfoUpdateVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@RestController
@RequestMapping(value = "info")
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
    public Result queryAdvInfo(Integer status,String name,String start,String end,Integer pageNum,Integer pageSize,String advType) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //判断日期是否合理
        AdvInfoExample example = new AdvInfoExample();
        if (pageNum == null || pageSize == null || status == null || advType == null) {//检查页码参数和广告状态是否为空
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_QUERYPARAMS_IS_NULL);
        }
        AdvTypeExample example1 = new AdvTypeExample();
        example1.createCriteria().andAdvtypeEqualTo(advType);
        List<AdvType> advTypes = advTypeService.selectByExample(example1);
        List<AdvInfo> advInfoList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        for (AdvType advType1 : advTypes) {
            advInfoList.add(advInfoService.selectAdvInfo(name, start, end, status, advType1.getId()));
        }
        return  Result.success(advInfoList);
    }




    /**
     * 删除广告信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "deleteAdvInfo", method = RequestMethod.POST)
    public Result deleteAdvInfo(Long id) {
        Result result = new Result();
                int a = advInfoService.deleteByPK(id);
                InfoMaterialExample example = new InfoMaterialExample();
                example.createCriteria().andAdvInfoIdEqualTo(id);
                int b = infoMaterialService.deleteByExample(example);
        toResult(a*b);
        return result;
    }

    /**
     * 批量删除广告信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfos(Long[] ids) {
        Result result = new Result();
        try {
            if (ids == null) {
                throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
            }
            for (Long id : ids) {
                //删除所有广告及所对应资源关系
                advInfoService.deleteByPK(id);
                InfoMaterialExample example = new InfoMaterialExample();
                example.createCriteria().andAdvInfoIdEqualTo(id);
                infoMaterialService.deleteByExample(example);
                   }
                Result.success();
        }catch(Exception e){
            e.printStackTrace();

        }
        return result;
    }


        /**
         * 创建广告信息
         *
         * @param advInfoInsertVO
         * @return
         */
        @Transactional(rollbackFor = Exception.class)
        @RequestMapping(value = "insert", method = RequestMethod.POST)
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
         *
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


    /**
     * 名称不为空，进一步判断
     * @param status
     * @param name
     * @param start
     * @param end
     * @param sdf
     * @param example
     * @throws ParseException
     */
        private void nameIsNotNull(List<Long> ids,Integer status, String name, String start, String end, SimpleDateFormat sdf, AdvInfoExample example) throws ParseException {
            if (StringUtils.isBlank(start) && StringUtils.isBlank(end)) {
                example.createCriteria().andStatusEqualTo(status).andNameEqualTo(name).andAdvTypeIdIn(ids);
            } else if (StringUtils.isBlank(start) && !StringUtils.isBlank(end)) {
                Date endDate = sdf.parse(end);
                example.createCriteria().andStatusEqualTo(status).andStartDateLessThanOrEqualTo(endDate).andNameEqualTo(name).andAdvTypeIdIn(ids);
            } else if (!StringUtils.isBlank(start) && StringUtils.isBlank(end)) {
                Date startDate =sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).andEndDateGreaterThanOrEqualTo(startDate).andNameEqualTo(name).andAdvTypeIdIn(ids);
            } else {
                Date endDate = sdf.parse(end);
                Date startDate =sdf.parse(start);
                example.createCriteria().andStartDateBetween(startDate, endDate);
            }
        }

    /**
     * 名称是空值进一步判断
     * @param status
     * @param start
     * @param end
     * @param sdf
     * @param example
     * @throws ParseException
     */
        private void nameIsNull(List<Long> ids,Integer status, String start, String end, SimpleDateFormat sdf, AdvInfoExample example) throws ParseException {
            if (StringUtils.isEmpty(start) && StringUtils.isEmpty(end)) {
                example.createCriteria().andStatusEqualTo(status).andAdvTypeIdIn(ids);
            } else if (StringUtils.isBlank(start)&&!StringUtils.isBlank(end)) {
                Date endDate = sdf.parse(end);
                example.createCriteria().andStatusEqualTo(status).andEndDateLessThanOrEqualTo(endDate).andAdvTypeIdIn(ids);
            } else if (!StringUtils.isBlank(start) && StringUtils.isBlank(end)) {
                Date startDate = sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).andStartDateGreaterThanOrEqualTo(startDate).andAdvTypeIdIn(ids);
            } else {
                Date endDate = sdf.parse(end);
                Date startDate = sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).
                        andStartDateGreaterThanOrEqualTo(startDate).andEndDateLessThanOrEqualTo(endDate).andAdvTypeIdIn(ids);
            }
        }
    }



















