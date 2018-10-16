package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.dao.MaterialTypeMapper;
import com.suma.pojo.*;
import com.suma.service.AdvMaterialService;
import com.suma.service.AdvTypeService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description: 广告素材增删改查
 */
@RestController
@RequestMapping("material")
public class AdvMaterialController {

    @Autowired
    private AdvMaterialService advMaterialService;
    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @RequestMapping("queryMaterial")
    public Result queryMaterial(Long advTypeId, Integer pageNum, Integer pageSize) {
        Result result = new Result();

        //通过关联表得到素材ID
        MaterialTypeExample example = new MaterialTypeExample();
        example.createCriteria().andAdvTypeIdEqualTo(advTypeId);
        List<MaterialType> materialTypes = materialTypeMapper.selectByExample(example);

        Long[] ids = new Long[materialTypes.size()];
        int i = 0;
        for (MaterialType materialType : materialTypes) {
            ids[i++] = materialType.getMaterialId();
        }
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdvMaterial> list = advMaterialService.findList(ids);

        result.setResultCode(0);
        result.setResultData(list);
        return result;
    }
}
