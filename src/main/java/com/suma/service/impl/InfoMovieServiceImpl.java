package com.suma.service.impl;

import com.suma.dao.BaseDAO;
import com.suma.dao.InfoMovieMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.InfoMovie;
import com.suma.pojo.InfoMovieExample;
import com.suma.service.InfoMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: luxinzong
 * @date: 2018/11/20 0020
 * @description
 */
@Service
public class InfoMovieServiceImpl extends BaseServiceImpl<InfoMovie,InfoMovieExample,Long> implements InfoMovieService {

    @Resource
    public void setBaseDao(InfoMovieMapper baseDao) {
        super.setBaseDao(baseDao);
    }
}
