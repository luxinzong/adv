package com.suma.dao;

import com.suma.pojo.AdvInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvInfoMapper {

    int deleteByAdvInfoId(@Param("id") Long id);

    int insertAdvInfo(AdvInfo advInf);

    AdvInfo selectByAdvInfoId(@Param("id") Long id);

  

    int updateAdvInfo(AdvInfo record);

}