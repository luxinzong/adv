package com.suma.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvCheckException;
import com.suma.exception.AdvFlyWordException;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.*;
import com.suma.service.*;
import com.suma.service.impl.AdvRegionService;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.vo.AdvCheckDetailVO;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author luxinzong
 * @date 2018/10/20
 * @description 广告审核接口
 */
@RestController
@RequestMapping(value = "check")
public class AdvCheckDetailController extends BaseController{

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
    private AdvRegionService advRegionService;

    /**
     * 保存审核信息
     * @param advCheckDetail 审核信息
     * @return Result 添加成功或失败
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result save(AdvCheckDetail advCheckDetail) {
        //判断参数是否为空，审核信息是否存在
        if (advCheckDetail == null || advCheckDetail.getAdvInfoId() == null ||
                advCheckDetail.getStatus() == null || advCheckDetail.getMark() == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        AdvInfo advInfo = advInfoService.findByPK(advCheckDetail.getAdvInfoId());
        advInfo.setStatus(advCheckDetail.getStatus());
        advInfo.setCheckNote(AdvContants.getCheckMap().get(advCheckDetail.getStatus())+advCheckDetail.getMark());
        advInfoService.update(advInfo);
        //判断该审核信息是否已存在，若存在更新，若不存在则插入审核信息
        if (advCheckService.select(advCheckDetail.getAdvInfoId()) != null) {
            return toResult(advCheckService.updateById(advCheckDetail));
        } else {
            return toResult(advCheckService.insert(advCheckDetail));
        }
    }

    /**
     * 查询所有审核信息
     * @param pageNum 页码
     * @param pageSize 显示条数
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "queryAll")
    public Result selectAll(Integer pageNum,Integer pageSize) {
        System.out.println(pageNum+","+pageSize+"哈哈哈哈哈");
        if (pageNum == null || pageSize == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        AdvInfoExample example = new AdvInfoExample();
        //把审核状态的广告信息查出来
        example.createCriteria().andStatusEqualTo(3);//待审核状态
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<AdvInfo> advInfoList = advInfoService.selectByExample(example);
        //前端显示对象集合，将对应广告信息存储到该集合中，返回给前端
        List<AdvCheckDetailVO> advCheckDetails = new ArrayList<>();
        //判断查出的广告信息集合是否为空
        if (!CollectionUtils.isEmpty(advInfoList)) {
            //遍历查到的所有广告信息，并将对应属性赋值给审核对象
            for (AdvInfo advInfo : advInfoList) {
                AdvCheckDetailVO advCheckDetailVO = new AdvCheckDetailVO();
                System.out.println(advInfo+"哈哈");
                BeanUtils.copyProperties(advInfo,advCheckDetailVO);
                System.out.println(advCheckDetailVO);
                //将审核意见传给前端页面
                if (advCheckService.select(advInfo.getId()) != null) {//避免数据库脏数据
                    advCheckDetailVO.setMark(advCheckService.select(advInfo.getId()).getMark());
                }
                //若广告类型ID存在则将广告类型和广告类型名称赋值给审核对象
                if (advInfo.getAdvTypeId() != null) {
                    advCheckDetailVO.setAdvTypeName( advTypeService.findByPK(advInfo.getAdvTypeId()).getAdvtypename());
                    advCheckDetailVO.setAdvType(advTypeService.findByPK(advInfo.getAdvTypeId()).getAdvtype());
                }
                //设置区域名称
                advCheckDetailVO.setRegionName(advRegionService.selectAdvRegionById(advInfo.getRegion()).getRegionName());
                advCheckDetails.add(advCheckDetailVO);
            }
        }
        PageInfo<AdvCheckDetailVO> pageInfo = new PageInfo<>(advCheckDetails);
        pageInfo.setTotal(page.getTotal());
        return Result.success(pageInfo);
    }

    /**
     * 查询单个广告审核信息详情
     * @param advInfoId 广告ID
     * @return 返回查询结果 -> List<AdvCheckDetail>
     */
    @RequestMapping(value = "query")
    public Result select(Long advInfoId) {
        if (advInfoId == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //创建审核信息前端VO对象
        AdvCheckDetailVO advCheckDetailVO = new AdvCheckDetailVO();
        //获取广告信息
        AdvInfo advInfo = advInfoService.findById(advInfoId);
        System.out.println(advInfo);
        //判断广告信息是否存在
        if (advInfo != null) {
            System.out.println(advInfo);
            //初始化广告位置信息
            AdvLocation advLocation = null;
            //判断类型ID是否存在
            if (advInfo.getAdvTypeId() != null) {
                //获取广告位置信息
                AdvLocationExample example = new AdvLocationExample();
                example.createCriteria().andAdvTypeIdEqualTo(advInfo.getAdvTypeId());
                if (advLocationService.selectByExample(example).size() != 0) {
                    advLocation = advLocationService.selectByExample(example).get(0);
                } else {
                    System.out.println("位置信息不存在");
                }
            }
            //获取字幕广告信息
            InfoFlyWordExample example1 = new InfoFlyWordExample();
            example1.createCriteria().andAdvInfoIdEqualTo(advInfoId);
            List<InfoFlyWord> infoFlyWords = infoFlywordService.selectByExample(example1);
            List<AdvFlyWord> advFlyWords = new ArrayList<>();
            if (CollectionUtils.isEmpty(infoFlyWords)) {
                System.out.println("字幕对应信息不存在");
            } else {
                for (InfoFlyWord infoFlyWord : infoFlyWords) {
                    advFlyWords.add(advFlywordService.findByPK(infoFlyWord.getFlywordId()));
                }
            }
            //获取频道信息
            if (advInfo.getReservedInt() == 0) {
                //所有频道
                advCheckDetailVO.setServiceGroupId(0l);
            } else{
                //按频道进行分组
                AdvInfoServiceGroupExample example = new AdvInfoServiceGroupExample();
                example.createCriteria()
                        .andAdvInfoIdEqualTo(advInfoId);
                List<AdvInfoServiceGroup> list = advInfoServiceGroupService.selectByExample(example);
                if (!CollectionUtils.isEmpty(list)) {
                    //设置分组名称
                    advCheckDetailVO.setServiceGroupName(serviceGroupService.findByPK(list.get(0).getServiceGroupId()).getGroupName());
                }
            }
            //区域划分
            advCheckDetailVO.setRegionName(advRegionService.selectAdvRegionById(advInfo.getRegion()).getRegionName());
            //给AdvCheckDetailVO页面对象属性赋值
            BeanUtils.copyProperties(advInfo, advCheckDetailVO);
            advCheckDetailVO.setAdvLocation(advLocation);
            advCheckDetailVO.setAdvFlyWords(advFlyWords);
            BeanUtils.copyProperties(advCheckService.select(advInfoId), advCheckDetailVO);
            return Result.success(advCheckDetailVO);
        } else {
            return Result.error("广告不存在");
        }
    }

    /**
     * 批量删除或免审 审核信息
     * @param str 审核信息ID字符串
     * @return 返回更新结果 Result 删除成功或失败
     */
    @RequestMapping(value = "delete")
    public Result delete(String str,Integer status) {
        //判断id字符串是否为空
        if (StringUtils.isBlank(str) || status == null) {
            throw new AdvCheckException(ExceptionConstants.ADV_CHECK_REQUESTPARAM_IS_NULL);
        }
        //将id字符串转解析成字符串数组，广告id
        List<Long> idsList = StringUtil.convertstr(str);
        for (Long id : idsList) {
           AdvInfo advInfo =  advInfoService.findByPK(id);
           advInfo.setStatus(status);
           AdvCheckDetail advCheckDetail = advCheckService.select(advInfo.getId());
           if (status == AdvContants.STATUS_EDIT) {
               advCheckService.deleteAll((Long[]) idsList.toArray());
           } else if (status == AdvContants.STATUS_PASS) {
               advCheckDetail.setMark("审核通过");
           }
           advInfoService.update(advInfo);
        }
        return Result.success();
    }
}
