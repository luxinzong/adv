package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.NetworkInfoMapper;
import com.suma.dao.TsInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.*;
import com.suma.service.ServiceInfoService;
import com.suma.utils.Result;
import com.suma.vo.AdvServiceVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/17
 * @description:
 */
@RestController
@RequestMapping("service")
public class AdvServiceController extends BaseController {


    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private TsInfoMapper tsInfoMapper;

    @RequestMapping("query")
    public Result queryService(AdvServiceVO serviceVO, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        List<Long> tsIds = new ArrayList<>();

        TsInfoExample tsExample = new TsInfoExample();
        TsInfoExample.Criteria tsCriteria = tsExample.createCriteria();
        if (serviceVO.getNetworkId() != null) {
            tsCriteria = tsCriteria.andNetworkIdEqualTo(serviceVO.getNetworkId());
        }
        if (serviceVO.getTsId() != null) {
            tsCriteria.andTsIdEqualTo(serviceVO.getTsId());
        }

        List<TsInfo> tsInfos = tsInfoMapper.selectByExample(tsExample);
        for (TsInfo tsInfo : tsInfos) {
            tsIds.add(tsInfo.getTsId());
        }

        if (tsIds.size() == 0 && (serviceVO.getNetworkId() != null || serviceVO.getTsId() != null)) {
            return Result.success(new PageInfo<ServiceInfo>(null));
        }

        ServiceInfoExample serviceExample = new ServiceInfoExample();
        ServiceInfoExample.Criteria criteria = serviceExample.createCriteria();
        if (tsIds.size() > 0) {
            criteria = criteria.andTsIdIn(tsIds);
        }
        if (serviceVO.getServiceId() != null) {
            criteria.andServiceIdEqualTo(serviceVO.getServiceId());
        }
        if (serviceVO.getServiceName() != null) {
            criteria.andServiceNameEqualTo(serviceVO.getServiceName());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceInfo> serviceInfos = serviceInfoService.selectByExample(serviceExample);

        return Result.success(new PageInfo<ServiceInfo>(serviceInfos));
    }
}
