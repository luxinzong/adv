package com.suma.service.impl;

import com.suma.constants.AdvContants;
import com.suma.dao.AdvFlyWordMapper;
import com.suma.dao.InfoFlyWordMapper;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.InfoFlywordService;
import com.suma.service.InfoMaterialService;
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
    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private InfoMaterialService infoMaterialService;

    @Override
    public List<Long> selectFlywordIds(Long advInfoId) {
        return infoFlyWordMapper.selectFlywordIds(advInfoId);
    }

    /**
     * 根据广告Id删除弹出广告对应资源信息
     * @param advInfoIds
     */
    @Override
    public void deleteByAdvInfoIds(List<Long> advInfoIds){
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            advInfoIds.forEach(advInfoId ->{
                AdvInfo advInfo = advInfoService.findByPK(advInfoId);
                Integer materialType = advInfo.getMaterialType();
                if (materialType.equals(AdvContants.TEXT_MATERIAL)) {
                    InfoFlyWordExample example = new InfoFlyWordExample();
                    example.createCriteria().andAdvInfoIdIn(advInfoIds);
                    infoFlyWordMapper.deleteByExample(example);
                }
                if (materialType.equals(AdvContants.VEDIO_MATERIAL)) {
                    infoMaterialService.deleteByAdvInfoId(advInfoId);
                }
            });
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
                advFlyWordMapper.updateByPrimaryKeySelective(advFlyWord);
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

    public void setFlyWordByOne() {

    }
}
