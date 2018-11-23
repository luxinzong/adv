package com.suma.service.impl;

import com.suma.dao.BaseDAO;
import com.suma.dao.MovieMaterialMapper;
import com.suma.pojo.MovieMaterial;
import com.suma.pojo.MovieMaterialExample;
import com.suma.service.MovieMaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: luxinzong
 * @date: 2018/11/20 0020
 * @description
 */
@Service
public class MovieMaterialServiceImpl extends BaseServiceImpl<MovieMaterial,MovieMaterialExample,Long> implements MovieMaterialService {

    @Resource
    public void setBaseDao(MovieMaterialMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
