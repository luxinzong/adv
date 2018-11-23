package com.suma.controller;

import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.service.AdvInfoService;
import com.suma.service.InfoRegionService;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import com.suma.utils.UserAndTimeUtils;
import com.suma.vo.AdvPutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: luxinzong
 * @date: 2018/11/19 0019
 * @description 广告投放管理
 */
@RestController
@RequestMapping("put")
public class AdvPutController extends BaseController {

    @Resource
    private AdvInfoService advInfoService;
    @Autowired
    private InfoRegionService infoRegionService;

    /**
     * 查询所有播发状态的广告
     * @param advPutVO
     * @return
     */
    @RequestMapping("query")
    public Result getAdvs(AdvPutVO advPutVO) {
        return Result.success(advInfoService.getPuttingAdv(advPutVO));
    }

    /***
     * 一键播发、暂停
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("putAndStop")
    public Result putAdvs(String ids,Integer status) {
        List<Long> advInfoIds = StringUtil.convertstr(ids);
        List<AdvInfo> advInfoList = advInfoService.getAdvByIds(advInfoIds);
        try {
            advInfoList.forEach(p -> {
                p.setStatus(status);
                UserAndTimeUtils.setEditUserAndTime(p);
            });
        } catch (Exception e) {
            e.printStackTrace();
            advInfoList.forEach(p -> {
                p.setStatus(AdvContants.STATUS_FAIL);
                UserAndTimeUtils.setEditUserAndTime(p);
            });
            throw e;
        }
        return Result.success();
    }


    /**
     * 停播当前区域或所有区域
     * @param str 区域
     * @return
     */
    @RequestMapping("stopByRegion")
    @Transactional(rollbackFor = Exception.class)
    public Result stopByRegion(String str) {
        List<Integer> regions = StringUtil.str2Int(str);
        List<AdvInfo> advInfoList = infoRegionService.getAdvByResion(regions);
        advInfoList.forEach(p->p.setStatus(AdvContants.STATUS_STOP));
        return Result.success();
    }


    /**
     * 如果是播发失败的情况，需要重新播发或者强制停播
     * @return
     */
    @RequestMapping("fail")
    @Transactional(rollbackFor = Exception.class)
    public Result dealAdvPutFail(String str,Integer status) {
        List<Long> advInfoIds = StringUtil.convertstr(str);
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andIdIn(advInfoIds).andStatusEqualTo(AdvContants.STATUS_FAIL);
        List<AdvInfo> advInfoList = advInfoService.selectByExample(example);
        advInfoList.forEach(p->p.setStatus(status));
        return Result.success();
    }

}
