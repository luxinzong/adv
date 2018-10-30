package com.suma.controller;

import com.google.common.base.Strings;
import com.suma.constants.ExceptionConstants;
import com.suma.dto.AdvRegionDto;
import com.suma.exception.RegionException;
import com.suma.pojo.AdvRegion;
import com.suma.service.iAdvRegionService;
import com.suma.utils.Result;
import com.suma.vo.AdvRegionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/26 0026
 * @Description 区域管理controller
 **/
@RestController
@RequestMapping("/system/region")
public class AdvRegionController extends BaseController{

    @Autowired
    private iAdvRegionService advRegionService;

    /**
     * 新增区域
     *
     * @param advRegionVO
     * @return
     */
    @RequestMapping("/add")
    public Result addAdvRegion(@Validated AdvRegionVO advRegionVO){
        AdvRegion advRegion = new AdvRegion();
        BeanUtils.copyProperties(advRegionVO,advRegion);

        return toResult(advRegionService.insertAdvRegion(advRegion));

    }

    @RequestMapping("/delete")
    public Result removeAdvRegion(Integer regionId){
        if(regionId == null){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_ID_IS_NULL);
        }

        return toResult(advRegionService.deleteAdvRegion(regionId));
    }

    @RequestMapping("/update")
    public Result updateAdvRegion(@Validated AdvRegionVO advRegionVO,Integer regionId){
        if(regionId == null){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_ID_IS_NULL);
        }

        AdvRegion advRegion = new AdvRegion();
        BeanUtils.copyProperties(advRegionVO,advRegion);
        advRegion.setRegionId(regionId);

        return toResult(advRegionService.updateAdvRegion(advRegion));
    }

    /**
     * 加载区域树列表
     *
     * @return
     */
    @RequestMapping("/list")
    public Result treeData(){
        List<AdvRegionDto> tree = advRegionService.selectAdvRegionTree();
        if(CollectionUtils.isEmpty(tree)){
            return Result.selectIsNullError();
        }
        return Result.success(tree);
    }


    /**
     * 根据条件查询部门
     *
     * @return
     */
    @RequestMapping("/query")
    public Result query(String regionName,String status){
        //如果不填参数，默认返回treeData
        if(Strings.isNullOrEmpty(regionName) && Strings.isNullOrEmpty(status)){
            return treeData();
        }

        //使用对象作为参数的目的，是为了扩展
        AdvRegion advRegion = new AdvRegion();
        advRegion.setRegionName(regionName);
        advRegion.setStatus(status);

        List<AdvRegionDto> advRegionDtoList = advRegionService.selectAdvRegionList(advRegion);
        if(CollectionUtils.isEmpty(advRegionDtoList)){
            return Result.selectIsNullError();
        }

        return Result.success(advRegionDtoList);
    }

    @RequestMapping("/listAll")
    public Result listAll(){
        List<AdvRegion> advRegionList = advRegionService.selectAdvRegionAll();
        //应前台要求在返回list中,增加无以便使用
        AdvRegion advRegion = new AdvRegion();
        advRegion.setRegionName("无");
        advRegion.setRegionId(0);

        advRegionList.add(0,advRegion);
        return Result.success(advRegionList);
    }

    @RequestMapping("/getAdvRegionCount")
    public Result getRegionCount(){
        return Result.success(advRegionService.getAdvRegionCount());
    }

}
