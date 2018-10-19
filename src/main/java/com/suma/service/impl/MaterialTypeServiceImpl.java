package com.suma.service.impl;

import com.suma.dao.MaterialTypeMapper;
import com.suma.pojo.MaterialType;
import com.suma.pojo.MaterialTypeExample;
import com.suma.service.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/16
 * @description:
 */
@Service
public class MaterialTypeServiceImpl extends BaseServiceImpl<MaterialType, MaterialTypeExample, Long> implements MaterialTypeService {

    @Resource
    public void setBaseDao(MaterialTypeMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public Long[] getMeterialByTypeId(Long advTypeId) {
        MaterialTypeExample example = new MaterialTypeExample();
        example.createCriteria().andAdvTypeIdEqualTo(advTypeId);
        List<MaterialType> materialTypes = materialTypeMapper.selectByExample(example);

        Long[] ids = new Long[materialTypes.size()];
        int i = 0;
        for (MaterialType materialType : materialTypes) {
            ids[i++] = materialType.getMaterialId();
        }
        return ids;
    }

    @Override
    public void deleteByMaterialId(Long id) {
        MaterialTypeExample example = new MaterialTypeExample();
        example.createCriteria().andMaterialIdEqualTo(id);
        materialTypeMapper.deleteByExample(example);
    }
}
