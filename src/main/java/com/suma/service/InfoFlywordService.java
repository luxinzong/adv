package com.suma.service;

import com.suma.pojo.AdvFlyWord;
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
    void deleteByAdvInfoIds(List<Long> advInfoIds);

    void saveFlyWords(List<AdvFlyWord> advFlyWords,Long advInfoId);

    void deletByAdvInfoId(Long advInfoId);

    List<AdvFlyWord> getAdvFlyWords(Long advInfoId);

}
