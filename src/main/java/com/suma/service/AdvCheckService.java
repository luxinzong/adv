package com.suma.service;

import com.suma.pojo.AdvCheckDetail;

import java.util.List;

/**
 * @author luxinzong
 * @date 2018/10/20
 * @description
 */
public interface AdvCheckService {
    int deleteById(Long id);

    int insert(AdvCheckDetail record);

    AdvCheckDetail select(Long id);

    List<AdvCheckDetail> selectAll();

    int updateById(AdvCheckDetail record);

    int deleteAll(Long[] ids);
}
