package com.suma.service;

import com.suma.pojo.AdvInfo;
import java.util.Date;
import java.util.List;

/**
 * @author luxinzong
 */
public interface AdvInfoService {
    void deleteByAdvInfoId(Long id);

    void insertAdvInfo(AdvInfo advInf);

    AdvInfo selectByAdvInfoId(Long id);

    List<AdvInfo> selectAdvInfos();

    List<AdvInfo> selectByNameAndStatusAndDate(String name, Integer status, Date startDate, Date endDate);

    void updateAdvInfo(AdvInfo record);
}
