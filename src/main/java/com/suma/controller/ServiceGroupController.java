
package com.suma.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.ServiceGroupMapper;
import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.ServiceGroup;
import com.suma.pojo.ServiceGroupExample;
import com.suma.pojo.ServiceInfoGroup;
import com.suma.pojo.ServiceInfoGroupExample;
import com.suma.service.ServiceGroupService;
import com.suma.service.ServiceInfoGroupService;
import com.suma.utils.Insert;
import com.suma.utils.Result;
import com.suma.utils.Update;
import com.suma.vo.ServiceGroupVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@RestController
@RequestMapping("serviceGroup")
public class ServiceGroupController extends BaseController {
    @Autowired
    private ServiceGroupService serviceGroupService;
    @Autowired
    private ServiceInfoGroupService serviceInfoGroupService;

    private Logger logger = LoggerFactory.getLogger(ServiceGroupController.class);

    @RequestMapping("query")
    public Result queryServiceGroup(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceGroup> serviceGroups = serviceGroupService.findALL();
        PageInfo<ServiceGroup> pageInfo = new PageInfo<>(serviceGroups);

        PageInfo<ServiceGroupVO> resPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, resPageInfo);
        List<ServiceGroupVO> serviceGroupVOs = new ArrayList<>();
        for (ServiceGroup serviceGroup : serviceGroups) {
            ServiceGroupVO serviceGroupVO = new ServiceGroupVO();
            BeanUtils.copyProperties(serviceGroup, serviceGroupVO);
            List<String> services = serviceInfoGroupService.findServicesByGroupId(serviceGroup.getSgid());
            serviceGroupVO.setServiceNames(services);
            serviceGroupVOs.add(serviceGroupVO);
        }
        resPageInfo.setList(serviceGroupVOs);
        return Result.success(resPageInfo);
    }

    @RequestMapping("add")
    public Result addServiceGroup(@Validated({Insert.class}) ServiceGroupVO serviceGroupVO) {
        List<ServiceGroup> serviceGroups = serviceGroupService.findByName(serviceGroupVO.getGroupName());
        if (serviceGroups != null && serviceGroups.size() > 0) {
            return Result.error("该分组名称已被占用");
        }

        ServiceGroup serviceGroup = new ServiceGroup();
        BeanUtils.copyProperties(serviceGroupVO, serviceGroup);
        serviceGroupService.save(serviceGroup, serviceGroupVO.getServiceNames());

        return Result.success();
    }

    @RequestMapping("update")
    public Result updateServiceGroup(@Validated({Update.class}) ServiceGroupVO serviceGroupVO) {
        List<ServiceGroup> serviceGroups = serviceGroupService.findByName(serviceGroupVO.getGroupName());
        if (serviceGroups != null && serviceGroups.size() > 0) {
            return Result.error("该分组名称已被占用");
        }

        ServiceGroup serviceGroup = new ServiceGroup();
        BeanUtils.copyProperties(serviceGroupVO, serviceGroup);
        serviceGroupService.update(serviceGroup, serviceGroupVO.getServiceNames());

        return Result.success();
    }


    @Transactional
    @RequestMapping("delete")
    public Result deleteServiceGroup(Long[] ids) {
        if (ids == null)
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);

        try {
            for (Long id : ids) {
                serviceGroupService.deleteByPK(id);
                serviceInfoGroupService.deleteByGroupId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("删除失败");
        }
        return Result.success();
    }
}
