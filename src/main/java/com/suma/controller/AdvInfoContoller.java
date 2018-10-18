package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.InfoMaterialService;
import com.suma.utils.Result;
import com.suma.vo.AdvInfoInsertVO;
import com.suma.vo.AdvInfoQueryVO;
import com.suma.vo.AdvInfoUpdateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    /**
     * 查询广告信息
     * @param
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public Result queryAdvInfo(Integer status,String name,String start,String end,Integer pageNum,Integer pageSize) throws ParseException {
        System.out.println("sssss"+status+name+start+end+pageNum+pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //判断日期是否合理
        AdvInfoExample example = new AdvInfoExample();
        if (pageNum == null || pageSize == null || status == null) {//检查页码参数和广告状态是否为空
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_QUERYPARAMS_IS_NULL);
        }
        if (name == null) {
            nameIsNull(status, start, end, sdf, example);
        } else {
            nameIsNotNull(status, name, start, end, sdf, example);
        }
        PageHelper.startPage(pageNum, pageSize);
        return  Result.success(advInfoService.selectByExample(example));
    }




    /**
     * 删除广告信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfo(Long id) {
        Result result = new Result();
        /*try {*/
           /* if (ids == null) {
                throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_MISSING_REQUIRED_PARAMS);
            }*/
     /* for (Long id : ids) {*/
                //删除所有广告及所对应资源关系
                int a = advInfoService.deleteByPK(id);
                InfoMaterialExample example = new InfoMaterialExample();
                example.createCriteria().andAdvInfoIdEqualTo(id);
                int b = infoMaterialService.deleteByExample(example);
           // }
        toResult(a*b);
       /* } catch (Exception e) {
            e.printStackTrace();
        }*/
        return result;
    }
    /*@RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result deleteAdvInfo(List<Long> ids) {
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
                *//*   }*//*
                Result.success();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }*/


        /**
         * 创建广告信息
         *
         * @param advInfoInsertVO
         * @return
         */
        @RequestMapping(value = "insert", method = RequestMethod.POST)
        public Result insertAdvInfo (AdvInfoInsertVO advInfoInsertVO) throws ParseException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            AdvInfo advInfo = new AdvInfo();
            advInfo.setStartDate(dateFormat.parse(advInfoInsertVO.getStart()));
            advInfo.setEndDate(dateFormat.parse(advInfoInsertVO.getEnd()));
            advInfo.setPeriodTimeEnd(df.parse(advInfoInsertVO.getPeriodTimeEnd()));
            advInfo.setPeriodTimeStart(df.parse(advInfoInsertVO.getPeriodTimeStart()));
            advInfo.setName(advInfoInsertVO.getName());
            System.out.println(advInfo);
            if (advInfo.getName() == null || advInfo.getStartDate() == null ||
                    advInfo.getEndDate() == null || advInfo.getPeriodTimeEnd() == null
                    || advInfo.getPeriodTimeStart() == null) {
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
            //若不存在,则创建广告信息以及对应广告资源
            return toResult(advInfoService.save(advInfo));
        }

        /**
         * 编辑广告信息
         *
         * @param advInfoUpdateVO
         * @return
         */
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
            return toResult(advInfoService.update(advInfo));
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
        private void nameIsNotNull(Integer status, String name, String start, String end, SimpleDateFormat sdf, AdvInfoExample example) throws ParseException {
            if (start == null && end == null) {
                example.createCriteria().andStatusEqualTo(status).andNameEqualTo(name);
            } else if (start == null && end != null) {
                Date endDate = sdf.parse(end);
                example.createCriteria().andStatusEqualTo(status).andEndDateLessThanOrEqualTo(endDate).andNameEqualTo(name);
            } else if (start != null && end == null) {
                Date startDate =sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).andStartDateGreaterThanOrEqualTo(startDate).andNameEqualTo(name);
            } else {
                Date endDate = sdf.parse(end);
                Date startDate =sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).
                        andStartDateGreaterThanOrEqualTo(startDate).andEndDateLessThanOrEqualTo(endDate).andNameEqualTo(name);
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
        private void nameIsNull(Integer status, String start, String end, SimpleDateFormat sdf, AdvInfoExample example) throws ParseException {
            if (start == null && end == null) {
                example.createCriteria().andStatusEqualTo(status);
            } else if (start == null && end != null) {
                Date endDate = sdf.parse(end);
                example.createCriteria().andStatusEqualTo(status).andEndDateLessThanOrEqualTo(endDate);
            } else if (start != null && end == null) {
                Date startDate = sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).andStartDateGreaterThanOrEqualTo(startDate);
            } else {
                Date endDate = sdf.parse(end);
                Date startDate = sdf.parse(start);
                example.createCriteria().andStatusEqualTo(status).
                        andStartDateGreaterThanOrEqualTo(startDate).andEndDateLessThanOrEqualTo(endDate);
            }
        }
    }



















