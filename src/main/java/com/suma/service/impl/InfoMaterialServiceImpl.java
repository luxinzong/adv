package com.suma.service.impl;

import com.suma.dao.InfoMaterialMapper;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;
import com.suma.service.InfoMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class InfoMaterialServiceImpl extends BaseServiceImpl<InfoMaterial,InfoMaterialExample,Long> implements InfoMaterialService {
    @Resource
    public void setBaseDao(InfoMaterialMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
