package com.suma.service;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
public interface AdvFlywordService extends BaseService<AdvFlyWord,AdvFlyWordExample,Long>{
    int saveAll(List<AdvFlyWord> advFlyWords);
}
