package com.suma.dao;

import com.suma.pojo.AdvInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdvInfoMapper {

    void deleteByAdvInfoId(@Param("id") Long id);

    void insertAdvInfo(AdvInfo advInf);

    AdvInfo selectByAdvInfoId(@Param("id") Long id);

    List<AdvInfo> selectAdvInfos();

    List<AdvInfo> selectByNameAndStatusAndDate(String Name, Integer status, Date startDate, Date endDate);

    void updateAdvInfo(AdvInfo record);

}