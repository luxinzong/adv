package com.suma.controller;

import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvRequestException;
import com.suma.exception.BaseException;
import com.suma.pojo.*;
import com.suma.service.AdvTypeService;
import com.suma.service.ServiceInfoGroupService;
import com.suma.service.ServiceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@RestController
public class AdvController {

    @Autowired
    private AdvTypeService advTypeService;
    @Autowired
    private ServiceInfoService serviceInfoService;
    @Autowired
    private ServiceInfoGroupService serviceInfoGroupService;

    @RequestMapping("getAdvShow")
    public AdvResponseVO getAdvShow(AdvRequestVO requestVO) {

        //判断参数
        if (StringUtils.isAnyBlank(requestVO.getSessionId(), requestVO.getClientId()) || requestVO.getReqNum() == null) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_MISSING_PARAMETERS, requestVO.getSessionId());
        }

        AdvResponseVO responseVO = new AdvResponseVO();
        responseVO.setSessionId(requestVO.getSessionId());

        //判断广告类型
        AdvTypeExample example = new AdvTypeExample();
        example.createCriteria().andAdvtypeEqualTo(requestVO.getAdvType()).andAdvsubtypeEqualTo(requestVO.getAdvSubType());
        List<AdvType> advTypes = advTypeService.selectByExample(example);
        if (CollectionUtils.isEmpty(advTypes)) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_TYPE_NOT_EXIST, requestVO.getSessionId());
        }

        //根据networkId,tsId,serviceId查询service
        AdvType advType = advTypes.get(0);
        ServiceInfoExample serviceInfoExample = new ServiceInfoExample();
        ServiceInfoExample.Criteria criteria = serviceInfoService
                .queryServiceByThreeId(requestVO.getNetworkID(), requestVO.getTsId(), requestVO.getServiceID(), serviceInfoExample);
        if (requestVO.getFreq() != null)
            criteria.andFreqEqualTo(requestVO.getFreq());
        List<ServiceInfo> serviceInfos = serviceInfoService.selectByExample(serviceInfoExample);
        if (CollectionUtils.isEmpty(serviceInfos)) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_SERVICE_NOT_EXIST, requestVO.getSessionId());
        }




        return null;
    }
}
