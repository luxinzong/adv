package com.suma.service;

import com.suma.pojo.AdvMaterial;
import com.suma.pojo.InfoMaterial;
import com.suma.pojo.InfoMaterialExample;

import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
public interface InfoMaterialService extends BaseService<InfoMaterial,InfoMaterialExample,Long> {
    int updateByDoubleId(InfoMaterial infoMaterial);

    List<AdvMaterial> findMaterialByAdv(Long id);

    List<InfoMaterial> findByAdv(Long id);
}
