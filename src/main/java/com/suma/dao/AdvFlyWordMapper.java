package com.suma.dao;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvFlyWordMapper  extends  BaseDAO<AdvFlyWord,AdvFlyWordExample,Long>{
    int saveAll(List<AdvFlyWord> advFlyWords);
}