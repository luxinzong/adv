package com.suma.service;

import com.suma.pojo.AdvType;

import java.util.List;

/**
 * @author luxinzong
 */

public interface AdvTypeService {
    void deleteByAdvTypeId(Long id);

    void insertAdvType(AdvType advType);

    AdvType selectByAdvTypeId(Long id);

    List<AdvType> selectAdvTypes();

    List<AdvType> selectAdvTypesByAdvType(String advType);

    void updateAdvType(AdvType advType);
}
