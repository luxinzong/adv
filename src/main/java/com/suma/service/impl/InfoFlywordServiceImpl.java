package com.suma.service.impl;

import com.suma.dao.AdvFlyWordMapper;
import com.suma.dao.InfoFlyWordMapper;
import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import com.suma.pojo.InfoFlyWord;
import com.suma.pojo.InfoFlyWordExample;
import com.suma.service.InfoFlywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Autowired
    private AdvFlyWordMapper advFlyWordMapper;

    @Override
    public List<Long> selectFlywordIds(Long advInfoId) {
        return infoFlyWordMapper.selectFlywordIds(advInfoId);
    }

    /**
     * 根据广告Id删除字幕信息
     * @param advInfoIds
     */
    @Override
    public void deleteByAdvInfoIds(List<Long> advInfoIds){
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            InfoFlyWordExample example = new InfoFlyWordExample();
            example.createCriteria().andAdvInfoIdIn(advInfoIds);
            infoFlyWordMapper.deleteByExample(example);
        }
    }

    @Override
    public void deletByAdvInfoId(Long advInfoId) {
        InfoFlyWordExample example = new InfoFlyWordExample();
        example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        infoFlyWordMapper.deleteByExample(example);
    }

    @Override
    public void saveFlyWords(List<AdvFlyWord> advFlyWords,Long advInfoId) {
        if (!CollectionUtils.isEmpty(advFlyWords)) {
            for (AdvFlyWord advFlyWord : advFlyWords) {
                advFlyWordMapper.insert(advFlyWord);
                InfoFlyWord infoFlyWord = new InfoFlyWord();
                infoFlyWord.setFlywordId(advFlyWord.getId());
                infoFlyWord.setAdvInfoId(advInfoId);
                infoFlyWordMapper.insert(infoFlyWord);
            }
        }
    }

    @Override
    public List<AdvFlyWord> getAdvFlyWords(Long advInfoId) {
        AdvFlyWordExample example1 = new AdvFlyWordExample();
        //创建字幕广告集合，用于存储广告对应的字幕信息
        List<AdvFlyWord> advFlyWords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(selectFlywordIds(advInfoId))) {
            //根据广告ID查询出对应的字幕广告ID
            example1.createCriteria().andIdIn(selectFlywordIds(advInfoId));
            advFlyWords = advFlyWordMapper.selectByExample(example1);
        }
        return advFlyWords;
    }
}
