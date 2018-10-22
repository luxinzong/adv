package com.suma.dao;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvFlyWordMapper extends BaseDAO<AdvFlyWord,AdvFlyWordExample,Long>{
    long countByExample(AdvFlyWordExample example);

    int deleteByExample(AdvFlyWordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdvFlyWord record);

    int insertSelective(AdvFlyWord record);

    List<AdvFlyWord> selectByExample(AdvFlyWordExample example);

    AdvFlyWord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdvFlyWord record, @Param("example") AdvFlyWordExample example);

    int updateByExample(@Param("record") AdvFlyWord record, @Param("example") AdvFlyWordExample example);

    int updateByPrimaryKeySelective(AdvFlyWord record);

    int updateByPrimaryKey(AdvFlyWord record);
}