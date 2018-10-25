package com.suma.dao;

import com.suma.pojo.InfoFlyWord;
import com.suma.pojo.InfoFlyWordExample;
import java.util.List;

public interface InfoFlyWordMapper extends BaseDAO<InfoFlyWord,InfoFlyWordExample,Long>{
    List<Long> selectFlywordIds(Long advInfoId);

}