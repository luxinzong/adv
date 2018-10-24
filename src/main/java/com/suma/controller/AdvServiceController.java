package com.suma.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.BaseException;
import com.suma.pojo.*;
import com.suma.service.NetworkService;
import com.suma.service.ServiceInfoService;
import com.suma.service.TsService;
import com.suma.utils.Insert;
import com.suma.utils.Result;
import com.suma.utils.Update;
import com.suma.vo.ServiceQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private TsService tsService;
    @Autowired
    private NetworkService networkService;

    @RequestMapping("query")
    public Result queryService(ServiceQueryVO serviceVO, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        ServiceInfoExample serviceExample = new ServiceInfoExample();
        ServiceInfoExample.Criteria criteria = serviceInfoService.queryServiceByThreeId(serviceVO.getNetworkId(), serviceVO.getTsId(), serviceVO.getServiceId(), serviceExample);

        if (criteria == null)
            return Result.success(new PageInfo<ServiceInfo>(null));
        if (serviceVO.getServiceName() != null) {
            criteria.andServiceNameEqualTo(serviceVO.getServiceName());
        }

        PageHelper.startPage(pageNum, pageSize);
        List<ServiceInfo> serviceInfos = serviceInfoService.selectByExample(serviceExample);
        PageInfo<ServiceInfo> oldPageInfo = new PageInfo<>(serviceInfos);

        List<ServiceQueryVO> serviceInfoVOs = new ArrayList<>();
        for (ServiceInfo serviceInfo : serviceInfos) {
            ServiceQueryVO vo = new ServiceQueryVO();
            BeanUtils.copyProperties(serviceInfo, vo);
            TsInfo tsInfo = tsService.findByPK(serviceInfo.getTid());
            vo.setNetworkId(networkService.findNetworkIdByPk(tsInfo.getNid()));
            vo.setTsName(tsInfo.getTsName());
            vo.setTsId(tsInfo.getTsId());
            NetworkInfo networkInfo = networkService.findByPK(tsInfo.getNid());
            if (networkInfo != null)
                vo.setNetworkName(networkInfo.getNetworkName());
            serviceInfoVOs.add(vo);
        }

        PageInfo<ServiceQueryVO> resultPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(oldPageInfo, resultPageInfo);
        resultPageInfo.setList(serviceInfoVOs);
        return Result.success(resultPageInfo);
    }


    @RequestMapping("getInfo")
    public Result getInfo(Long id) {
        if (id == null) {
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        ServiceInfo service = serviceInfoService.findByPK(id);
        return Result.success(service);
    }


    @RequestMapping("update")
    public Result updateService(@Validated({Update.class}) ServiceInfo serviceInfo) {
        serviceInfoService.checkDuplicate(serviceInfo);
        ServiceInfo oldInfo = serviceInfoService.findByPK(serviceInfo.getId());
        oldInfo.setServiceId(serviceInfo.getServiceId());
        oldInfo.setServiceName(serviceInfo.getServiceName());
        oldInfo.setType(serviceInfo.getType());
        return toResult(serviceInfoService.update(oldInfo));
    }

    @RequestMapping("delete")
    public Result deleteService(Long[] ids) {
        if (ids == null)
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);

        for (Long id : ids) {
            serviceInfoService.deleteByPK(id);
        }

        return Result.success();
    }

    @RequestMapping("add")
    public Result addService(@Validated({Insert.class}) ServiceInfo serviceInfo) {
        serviceInfoService.checkDuplicate(serviceInfo);
        return toResult(serviceInfoService.save(serviceInfo));
    }


}
