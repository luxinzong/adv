package com.suma.service.impl;

import com.suma.dao.AdvLocationMapper;
import com.suma.dao.AdvMaterialMapper;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.service.AdvMaterialService;
import com.suma.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
@Service
public class AdvMaterialServiceImpl extends BaseServiceImpl<AdvMaterial, AdvMaterialExample, Long> implements AdvMaterialService {
    @Resource
    public void setBaseDao(AdvMaterialMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }
}
