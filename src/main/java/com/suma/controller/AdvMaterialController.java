package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.MaterialException;
import com.suma.pojo.*;
import com.suma.service.AdvMaterialService;
import com.suma.service.MaterialTypeService;
import com.suma.utils.Result;
import com.suma.vo.AdvMaterialVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description: 广告素材增删改查
 */
@RestController
@RequestMapping("material")
public class AdvMaterialController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AdvMaterialController.class);

    @Autowired
    private AdvMaterialService advMaterialService;
    @Autowired
    private MaterialTypeService materialTypeService;

    @RequestMapping("query")
    public Result queryMaterial(Long advTypeId, Integer materialType, Integer pageNum, Integer pageSize) {

        if (advTypeId == null || materialType == null || pageNum == null || pageSize == null) {
            throw new MaterialException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        //通过关联表得到素材ID
        Long[] ids = materialTypeService.getMeterialByTypeId(advTypeId);
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdvMaterial> list = advMaterialService.findListByMaterialType(ids, materialType);
        PageInfo<AdvMaterial> pageInfoOld = new PageInfo<>(list);

        List<AdvMaterialVO> VOList = new ArrayList<>();
        for (AdvMaterial material : list) {
            AdvMaterialVO vo = new AdvMaterialVO();
            BeanUtils.copyProperties(material, vo);
            VOList.add(vo);
        }
        PageInfo<AdvMaterialVO> pageInfoResult = new PageInfo<>();
        BeanUtils.copyProperties(pageInfoOld, pageInfoResult);
        pageInfoResult.setList(VOList);

        return Result.success(pageInfoResult);
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("add")
    public Result uploadMaterial(String typeIds, AdvMaterialVO materialVO, MultipartFile file) {
        if (typeIds == null || file == null || materialVO.getMaterialType() == null) {
            throw new MaterialException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        advMaterialService.uploadMeterial(typeIds, materialVO.getFileName(), file, materialVO.getHref(), materialVO.getMaterialType());
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("update")
    public Result updateMaterial(AdvMaterialVO materialVO) {
        if (materialVO.getTypeIds() == null || materialVO.getFileName() == null || materialVO.getId() == null) {
            throw new MaterialException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }

        AdvMaterial materialPojo = advMaterialService.findByPK(materialVO.getId());
        materialPojo.setFileName(materialVO.getFileName());
        if (materialVO.getHref() != null)
            materialPojo.setHref(materialVO.getHref());

        advMaterialService.update(materialPojo, materialVO.getTypeIds());
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("delete")
    public Result deleteMaterial(Long[] ids) {
        if (ids == null) {
            throw new MaterialException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }
        for (Long id : ids) {
            advMaterialService.cascadeDelete(id);
        }
        return Result.success();
    }

    @RequestMapping("getDetail")
    public Result getDetail(Long id) {
        AdvMaterial material = advMaterialService.findByPK(id);
        AdvMaterialVO vo = new AdvMaterialVO();
        vo.setFileName(material.getFileName());
        vo.setHref(material.getHref());
        String typeIds = materialTypeService.getTypeIdsByMaterialId(id);
        vo.setTypeIds(typeIds);
        return Result.success(vo);
    }


}
