package com.suma.service.impl;

import com.suma.dao.AdvFlyWordMapper;
import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
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

 @Override
 public int saveAll(List<AdvFlyWord> advFlyWords) {
  return advFlyWordMapper.saveAll(advFlyWords);
 }
}
