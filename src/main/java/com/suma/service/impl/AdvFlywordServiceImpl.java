package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvFlyWordMapper;
import com.suma.dao.InfoFlyWordMapper;
import com.suma.exception.AdvFlyWordException;
import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import com.suma.pojo.InfoFlyWordExample;
import com.suma.service.AdvFlywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
 @Service
public class AdvFlywordServiceImpl extends BaseServiceImpl<AdvFlyWord,AdvFlyWordExample,Long> implements AdvFlywordService {

 @Resource
 public void setBaseDao(AdvFlyWordMapper advFlyWordMapper) {
  super.setBaseDao(advFlyWordMapper);
 }

 @Autowired
 private AdvFlyWordMapper advFlyWordMapper;
 @Autowired
 private InfoFlyWordMapper infoFlyWordMapper;
 @Override
 public int saveAll(List<AdvFlyWord> advFlyWords) {
  return advFlyWordMapper.saveAll(advFlyWords);
 }

 @Override
 public int save(AdvFlyWord advFlyWord) {
  if (advFlyWord.getContent() == null || advFlyWord.getBackgroundColour() == null || advFlyWord.getDirect() == null
          || advFlyWord.getDisplayTimes() == null || advFlyWord.getDuration() == null || advFlyWord.getFontColour() == null
          || advFlyWord.getFontSize() == null || advFlyWord.getIntervalTime() == null || advFlyWord.getSpeed() == null) {
   throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
  }
  return super.save(advFlyWord);
 }

 @Override
 public int deleteByPK(Long aLong) {
  InfoFlyWordExample example = new InfoFlyWordExample();
  example.createCriteria().andFlywordIdEqualTo(aLong);
  infoFlyWordMapper.deleteByExample(example);
  return super.deleteByPK(aLong);
 }
}
