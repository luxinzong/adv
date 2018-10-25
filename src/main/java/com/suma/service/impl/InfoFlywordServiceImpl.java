package com.suma.service.impl;

import com.suma.dao.InfoFlyWordMapper;
import com.suma.pojo.InfoFlyWord;
import com.suma.pojo.InfoFlyWordExample;
import com.suma.service.InfoFlywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/25 0025
 * @description
 */
@Service
public class InfoFlywordServiceImpl extends BaseServiceImpl<InfoFlyWord,InfoFlyWordExample,Long> implements InfoFlywordService {

    @Resource
    public void setBaseDao(InfoFlyWordMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private InfoFlyWordMapper infoFlyWordMapper;

    @Override
    public List<Long> selectFlywordIds(Long advInfoId) {
        return infoFlyWordMapper.selectFlywordIds(advInfoId);
    }

}
