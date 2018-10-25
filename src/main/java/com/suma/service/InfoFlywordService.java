package com.suma.service;

import com.suma.pojo.InfoFlyWord;
import com.suma.pojo.InfoFlyWordExample;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/25 0025
 * @description
 */
public interface InfoFlywordService extends BaseService<InfoFlyWord,InfoFlyWordExample,Long> {
    List<Long> selectFlywordIds(Long advInfoId);

}
